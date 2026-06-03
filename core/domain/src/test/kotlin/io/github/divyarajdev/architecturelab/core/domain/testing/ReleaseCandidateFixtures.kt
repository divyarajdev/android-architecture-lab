package io.github.divyarajdev.architecturelab.core.domain.testing

import io.github.divyarajdev.architecturelab.core.common.UtcEpochMillis
import io.github.divyarajdev.architecturelab.core.model.ModuleHealth
import io.github.divyarajdev.architecturelab.core.model.ModuleHealthStatus
import io.github.divyarajdev.architecturelab.core.model.QualityGate
import io.github.divyarajdev.architecturelab.core.model.QualityGateStatus
import io.github.divyarajdev.architecturelab.core.model.ReleaseCandidate
import io.github.divyarajdev.architecturelab.core.model.ReleaseCandidateId
import io.github.divyarajdev.architecturelab.core.model.ReleaseStatus

object ReleaseCandidateFixtures {
    const val RELEASE_CANDIDATE_ID = "release-2026.06.01"
    const val RELEASE_VERSION_NAME = "2026.06.01"
    const val APP_MODULE_NAME = "app"
    const val DOMAIN_MODULE_NAME = "core:domain"
    const val UNIT_TESTS_GATE_NAME = "Unit tests"
    const val ANDROID_LINT_GATE_NAME = "Android lint"
    const val PERFORMANCE_REVIEW_GATE_NAME = "Performance review"

    val updatedAt = UtcEpochMillis(1_780_272_000_000)

    fun releaseCandidate(
        status: ReleaseStatus = ReleaseStatus.IN_VALIDATION,
        qualityGates: List<QualityGate> = listOf(
            qualityGate(UNIT_TESTS_GATE_NAME, QualityGateStatus.PASSED),
        ),
        moduleHealth: List<ModuleHealth> = listOf(healthyModule(APP_MODULE_NAME)),
    ): ReleaseCandidate = ReleaseCandidate(
        id = ReleaseCandidateId(RELEASE_CANDIDATE_ID),
        versionName = RELEASE_VERSION_NAME,
        status = status,
        qualityGates = qualityGates,
        moduleHealth = moduleHealth,
        updatedAt = updatedAt,
    )

    fun qualityGate(
        name: String,
        status: QualityGateStatus,
        isRequired: Boolean = true,
    ): QualityGate = QualityGate(
        name = name,
        status = status,
        isRequired = isRequired,
    )

    fun healthyModule(moduleName: String = APP_MODULE_NAME): ModuleHealth =
        moduleHealth(moduleName, ModuleHealthStatus.HEALTHY)

    fun moduleHealth(
        moduleName: String,
        status: ModuleHealthStatus,
        openDefectCount: Int = 0,
    ): ModuleHealth = ModuleHealth(
        moduleName = moduleName,
        status = status,
        openDefectCount = openDefectCount,
    )
}
