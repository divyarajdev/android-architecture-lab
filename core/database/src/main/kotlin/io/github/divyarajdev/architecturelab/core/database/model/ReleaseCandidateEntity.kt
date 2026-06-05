package io.github.divyarajdev.architecturelab.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room entity for cached release candidate metadata.
 */
@Entity(tableName = "release_candidates")
data class ReleaseCandidateEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "version_name")
    val versionName: String,
    @ColumnInfo(name = "status")
    val status: String,
    @ColumnInfo(name = "updated_at")
    val updatedAt: Long,
)
