package io.github.divyarajdev.architecturelab.core.data.mapper

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

/**
 * Maps a local aggregate to a domain release candidate.
 */
fun ReleaseCandidateAggregate.toDomain(): ReleaseCandidate = ReleaseCandidate(
    id = ReleaseCandidateId(releaseCandidate.id),
    versionName = releaseCandidate.versionName,
    status = enumValueOf<ReleaseStatus>(releaseCandidate.status),
    qualityGates = qualityGates.map(QualityGateEntity::toDomain),
    moduleHealth = moduleHealth.map(ModuleHealthEntity::toDomain),
    updatedAt = UtcEpochMillis(releaseCandidate.updatedAt),
)

/**
 * Maps a remote DTO to the local aggregate used as source of truth.
 */
fun NetworkReleaseCandidate.toLocalAggregate(): ReleaseCandidateAggregate = ReleaseCandidateAggregate(
    releaseCandidate = ReleaseCandidateEntity(
        id = id,
        versionName = versionName,
        status = status,
        updatedAt = updatedAt,
    ),
    qualityGates = qualityGates.map { qualityGate -> qualityGate.toLocalEntity(id) },
    moduleHealth = moduleHealth.map { moduleHealth -> moduleHealth.toLocalEntity(id) },
)

private fun QualityGateEntity.toDomain(): QualityGate = QualityGate(
    name = name,
    status = enumValueOf<QualityGateStatus>(status),
    isRequired = isRequired,
)

private fun ModuleHealthEntity.toDomain(): ModuleHealth = ModuleHealth(
    moduleName = moduleName,
    status = enumValueOf<ModuleHealthStatus>(status),
    openDefectCount = openDefectCount,
)

private fun NetworkQualityGate.toLocalEntity(releaseCandidateId: String): QualityGateEntity = QualityGateEntity(
    releaseCandidateId = releaseCandidateId,
    name = name,
    status = status,
    isRequired = isRequired,
)

private fun NetworkModuleHealth.toLocalEntity(releaseCandidateId: String): ModuleHealthEntity = ModuleHealthEntity(
    releaseCandidateId = releaseCandidateId,
    moduleName = moduleName,
    status = status,
    openDefectCount = openDefectCount,
)
