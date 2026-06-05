package io.github.divyarajdev.architecturelab.core.database.model

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Room projection for a release candidate and its child records.
 */
data class ReleaseCandidateAggregate(
    @Embedded
    val releaseCandidate: ReleaseCandidateEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "release_candidate_id",
    )
    val qualityGates: List<QualityGateEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "release_candidate_id",
    )
    val moduleHealth: List<ModuleHealthEntity>,
)
