package io.github.divyarajdev.architecturelab.core.domain.usecase

import io.github.divyarajdev.architecturelab.core.domain.testing.ReleaseCandidateFixtures.ANDROID_LINT_GATE_NAME
import io.github.divyarajdev.architecturelab.core.domain.testing.ReleaseCandidateFixtures.PERFORMANCE_REVIEW_GATE_NAME
import io.github.divyarajdev.architecturelab.core.domain.testing.ReleaseCandidateFixtures.UNIT_TESTS_GATE_NAME
import io.github.divyarajdev.architecturelab.core.domain.testing.ReleaseCandidateFixtures.qualityGate
import io.github.divyarajdev.architecturelab.core.domain.testing.ReleaseCandidateFixtures.releaseCandidate
import io.github.divyarajdev.architecturelab.core.model.QualityGateStatus
import io.github.divyarajdev.architecturelab.core.model.ReadinessDecision
import io.github.divyarajdev.architecturelab.core.model.ReadinessReason
import org.junit.Assert.assertEquals
import org.junit.Test

class EvaluateReleaseReadinessUseCaseTest {
    private val useCase = EvaluateReleaseReadinessUseCase()

    @Test
    fun `returns Ready when required quality gates pass`() {
        val decision = useCase(
            releaseCandidate(
                qualityGates = listOf(
                    qualityGate(UNIT_TESTS_GATE_NAME, QualityGateStatus.PASSED),
                    qualityGate(ANDROID_LINT_GATE_NAME, QualityGateStatus.PASSED),
                ),
            ),
        )

        assertEquals(ReadinessDecision.Ready, decision)
    }

    @Test
    fun `returns Blocked when a critical quality gate fails`() {
        val decision = useCase(
            releaseCandidate(
                qualityGates = listOf(
                    qualityGate(UNIT_TESTS_GATE_NAME, QualityGateStatus.PASSED),
                    qualityGate(ANDROID_LINT_GATE_NAME, QualityGateStatus.FAILED),
                ),
            ),
        )

        assertEquals(
            ReadinessDecision.Blocked(
                reasons = listOf(
                    ReadinessReason.RequiredQualityGateFailed(ANDROID_LINT_GATE_NAME),
                ),
            ),
            decision,
        )
    }

    @Test
    fun `returns NeedsAttention when a warning quality gate exists`() {
        val decision = useCase(
            releaseCandidate(
                qualityGates = listOf(
                    qualityGate(UNIT_TESTS_GATE_NAME, QualityGateStatus.PASSED),
                    qualityGate(PERFORMANCE_REVIEW_GATE_NAME, QualityGateStatus.WARNING),
                ),
            ),
        )

        assertEquals(
            ReadinessDecision.NeedsAttention(
                reasons = listOf(
                    ReadinessReason.QualityGateWarning(PERFORMANCE_REVIEW_GATE_NAME),
                ),
            ),
            decision,
        )
    }

    @Test
    fun `returns NeedsAttention when required evidence is incomplete`() {
        val decision = useCase(
            releaseCandidate(
                qualityGates = listOf(
                    qualityGate(UNIT_TESTS_GATE_NAME, QualityGateStatus.NOT_RUN),
                ),
            ),
        )

        assertEquals(
            ReadinessDecision.NeedsAttention(
                reasons = listOf(
                    ReadinessReason.RequiredQualityGateNotRun(UNIT_TESTS_GATE_NAME),
                ),
            ),
            decision,
        )
    }
}
