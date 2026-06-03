package io.github.divyarajdev.architecturelab.core.domain.usecase

import io.github.divyarajdev.architecturelab.core.model.ModuleHealthStatus
import io.github.divyarajdev.architecturelab.core.model.QualityGateStatus
import io.github.divyarajdev.architecturelab.core.model.ReadinessDecision
import io.github.divyarajdev.architecturelab.core.model.ReadinessReason
import io.github.divyarajdev.architecturelab.core.model.ReleaseCandidate
import io.github.divyarajdev.architecturelab.core.model.ReleaseStatus

/**
 * Evaluates release readiness from domain state only.
 *
 * The rule order is intentional: blocking reasons take precedence over attention reasons. A release
 * candidate is ready only when no blocking or attention reasons are produced.
 */
class EvaluateReleaseReadinessUseCase {
    /**
     * Evaluates one release candidate.
     *
     * @param candidate release candidate to evaluate.
     * @return readiness decision derived from status, quality gates, and module health.
     */
    operator fun invoke(candidate: ReleaseCandidate): ReadinessDecision {
        val blockingReasons = buildBlockingReasons(candidate)

        if (blockingReasons.isNotEmpty()) {
            return ReadinessDecision.Blocked(blockingReasons)
        }

        val attentionReasons = buildAttentionReasons(candidate)

        return if (attentionReasons.isEmpty()) {
            ReadinessDecision.Ready
        } else {
            ReadinessDecision.NeedsAttention(attentionReasons)
        }
    }

    private fun buildBlockingReasons(candidate: ReleaseCandidate): List<ReadinessReason> =
        buildList {
            if (candidate.status == ReleaseStatus.BLOCKED) {
                add(ReadinessReason.ReleaseCandidateBlocked)
            }

            candidate.qualityGates
                .filter { qualityGate ->
                    qualityGate.isRequired && qualityGate.status == QualityGateStatus.FAILED
                }.forEach { qualityGate ->
                    add(ReadinessReason.RequiredQualityGateFailed(qualityGate.name))
                }

            candidate.moduleHealth
                .filter { moduleHealth -> moduleHealth.status == ModuleHealthStatus.BLOCKED }
                .forEach { moduleHealth ->
                    add(ReadinessReason.ModuleBlocked(moduleHealth.moduleName))
                }
        }

    private fun buildAttentionReasons(candidate: ReleaseCandidate): List<ReadinessReason> =
        buildList {
            if (candidate.qualityGates.isEmpty()) {
                add(ReadinessReason.QualityGateEvidenceMissing)
            }

            candidate.qualityGates
                .filter { qualityGate -> qualityGate.status == QualityGateStatus.WARNING }
                .forEach { qualityGate ->
                    add(ReadinessReason.QualityGateWarning(qualityGate.name))
                }

            candidate.qualityGates
                .filter { qualityGate ->
                    qualityGate.isRequired && qualityGate.status == QualityGateStatus.NOT_RUN
                }.forEach { qualityGate ->
                    add(ReadinessReason.RequiredQualityGateNotRun(qualityGate.name))
                }

            candidate.moduleHealth
                .filter { moduleHealth -> moduleHealth.status == ModuleHealthStatus.DEGRADED }
                .forEach { moduleHealth ->
                    add(ReadinessReason.ModuleDegraded(moduleHealth.moduleName))
                }

            candidate.moduleHealth
                .filter { moduleHealth -> moduleHealth.status == ModuleHealthStatus.NOT_EVALUATED }
                .forEach { moduleHealth ->
                    add(ReadinessReason.ModuleNotEvaluated(moduleHealth.moduleName))
                }
        }
}
