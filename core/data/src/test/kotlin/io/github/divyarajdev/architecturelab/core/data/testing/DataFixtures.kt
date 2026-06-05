package io.github.divyarajdev.architecturelab.core.data.testing

import io.github.divyarajdev.architecturelab.core.common.UtcEpochMillis
import io.github.divyarajdev.architecturelab.core.database.model.ModuleHealthEntity
import io.github.divyarajdev.architecturelab.core.database.model.QualityGateEntity
import io.github.divyarajdev.architecturelab.core.database.model.ReleaseCandidateAggregate
import io.github.divyarajdev.architecturelab.core.database.model.ReleaseCandidateEntity
import io.github.divyarajdev.architecturelab.core.model.ModuleHealth
import io.github.divyarajdev.architecturelab.core.model.ModuleHealthStatus
import io.github.divyarajdev.architecturelab.core.model.QualityGate
import io.github.divyarajdev.architecturelab.core.model.QualityGateStatus
import io.github.divyarajdev.architecturelab.core.model.ReleaseCandidate
import io.github.divyarajdev.architecturelab.core.model.ReleaseCandidateId
import io.github.divyarajdev.architecturelab.core.model.ReleaseStatus
import io.github.divyarajdev.architecturelab.core.network.model.NetworkModuleHealth
import io.github.divyarajdev.architecturelab.core.network.model.NetworkQualityGate
import io.github.divyarajdev.architecturelab.core.network.model.NetworkReleaseCandidate

object DataFixtures {
    const val RELEASE_CANDIDATE_ID = "release-2026.06.15"
    const val RELEASE_VERSION_NAME = "2026.06.15"
    const val UPDATED_AT = 1_781_484_400_000L
    const val UNIT_TESTS_GATE_NAME = "Unit tests"
    const val DATA_MODULE_NAME = "core:data"

    fun localAggregate(): ReleaseCandidateAggregate = ReleaseCandidateAggregate(
        releaseCandidate = ReleaseCandidateEntity(
            id = RELEASE_CANDIDATE_ID,
            versionName = RELEASE_VERSION_NAME,
            status = ReleaseStatus.IN_VALIDATION.name,
            updatedAt = UPDATED_AT,
        ),
        qualityGates = listOf(
            QualityGateEntity(
                releaseCandidateId = RELEASE_CANDIDATE_ID,
                name = UNIT_TESTS_GATE_NAME,
                status = QualityGateStatus.PASSED.name,
                isRequired = true,
            ),
        ),
        moduleHealth = listOf(
            ModuleHealthEntity(
                releaseCandidateId = RELEASE_CANDIDATE_ID,
                moduleName = DATA_MODULE_NAME,
                status = ModuleHealthStatus.HEALTHY.name,
                openDefectCount = 0,
            ),
        ),
    )

    fun networkReleaseCandidate(): NetworkReleaseCandidate = NetworkReleaseCandidate(
        id = RELEASE_CANDIDATE_ID,
        versionName = RELEASE_VERSION_NAME,
        status = ReleaseStatus.IN_VALIDATION.name,
        qualityGates = listOf(
            NetworkQualityGate(
                name = UNIT_TESTS_GATE_NAME,
                status = QualityGateStatus.PASSED.name,
                isRequired = true,
            ),
        ),
        moduleHealth = listOf(
            NetworkModuleHealth(
                moduleName = DATA_MODULE_NAME,
                status = ModuleHealthStatus.HEALTHY.name,
                openDefectCount = 0,
            ),
        ),
        updatedAt = UPDATED_AT,
    )

    fun domainReleaseCandidate(): ReleaseCandidate = ReleaseCandidate(
        id = ReleaseCandidateId(RELEASE_CANDIDATE_ID),
        versionName = RELEASE_VERSION_NAME,
        status = ReleaseStatus.IN_VALIDATION,
        qualityGates = listOf(
            QualityGate(
                name = UNIT_TESTS_GATE_NAME,
                status = QualityGateStatus.PASSED,
                isRequired = true,
            ),
        ),
        moduleHealth = listOf(
            ModuleHealth(
                moduleName = DATA_MODULE_NAME,
                status = ModuleHealthStatus.HEALTHY,
                openDefectCount = 0,
            ),
        ),
        updatedAt = UtcEpochMillis(UPDATED_AT),
    )
}
