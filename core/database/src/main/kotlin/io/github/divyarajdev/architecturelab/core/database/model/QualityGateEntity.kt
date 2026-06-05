package io.github.divyarajdev.architecturelab.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

/**
 * Room entity for cached quality gate state.
 */
@Entity(
    tableName = "quality_gates",
    primaryKeys = ["release_candidate_id", "name"],
    foreignKeys = [
        ForeignKey(
            entity = ReleaseCandidateEntity::class,
            parentColumns = ["id"],
            childColumns = ["release_candidate_id"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    indices = [
        Index("release_candidate_id"),
    ],
)
data class QualityGateEntity(
    @ColumnInfo(name = "release_candidate_id")
    val releaseCandidateId: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "status")
    val status: String,
    @ColumnInfo(name = "is_required")
    val isRequired: Boolean,
)
