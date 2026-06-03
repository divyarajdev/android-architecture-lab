package io.github.divyarajdev.architecturelab.core.model

import io.github.divyarajdev.architecturelab.core.common.UtcEpochMillis

/**
 * Domain representation of a release candidate in the Release Readiness Lab scope.
 *
 * The model is framework independent and must not expose persistence, network, dependency
 * injection, or UI implementation types.
 *
 * @property id stable release candidate identifier.
 * @property versionName public version name shown in release readiness workflows.
 * @property status current release lifecycle state.
 * @property qualityGates validation gates used to evaluate release readiness.
 * @property moduleHealth module-level health signals used by readiness evaluation.
 * @property updatedAt timestamp for the latest known release candidate state.
 */
data class ReleaseCandidate(
    val id: ReleaseCandidateId,
    val versionName: String,
    val status: ReleaseStatus,
    val qualityGates: List<QualityGate>,
    val moduleHealth: List<ModuleHealth>,
    val updatedAt: UtcEpochMillis,
) {
    init {
        require(versionName.isNotBlank()) { "Release candidate version name must not be blank." }
    }
}

/**
 * Stable identifier for a [ReleaseCandidate].
 *
 * @property value non-blank identifier value.
 */
@JvmInline
value class ReleaseCandidateId(
    val value: String,
) {
    init {
        require(value.isNotBlank()) { "Release candidate id must not be blank." }
    }
}

/**
 * Release lifecycle state used by domain readiness rules.
 */
enum class ReleaseStatus {
    DRAFT,
    IN_VALIDATION,
    READY_FOR_RELEASE,
    RELEASED,
    BLOCKED,
}
