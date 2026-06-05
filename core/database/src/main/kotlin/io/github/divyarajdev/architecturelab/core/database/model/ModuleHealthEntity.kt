package io.github.divyarajdev.architecturelab.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

/**
 * Room entity for cached module health state.
 */
@Entity(
    tableName = "module_health",
    primaryKeys = ["release_candidate_id", "module_name"],
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
data class ModuleHealthEntity(
    @ColumnInfo(name = "release_candidate_id")
    val releaseCandidateId: String,
    @ColumnInfo(name = "module_name")
    val moduleName: String,
    @ColumnInfo(name = "status")
    val status: String,
    @ColumnInfo(name = "open_defect_count")
    val openDefectCount: Int,
)
