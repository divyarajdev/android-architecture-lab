package io.github.divyarajdev.architecturelab.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.divyarajdev.architecturelab.core.database.dao.ReleaseCandidateDao
import io.github.divyarajdev.architecturelab.core.database.model.ModuleHealthEntity
import io.github.divyarajdev.architecturelab.core.database.model.QualityGateEntity
import io.github.divyarajdev.architecturelab.core.database.model.ReleaseCandidateEntity

/**
 * Room database for local Release Readiness Lab data.
 */
@Database(
    entities = [
        ReleaseCandidateEntity::class,
        QualityGateEntity::class,
        ModuleHealthEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
abstract class ArchitectureLabDatabase : RoomDatabase() {
    /**
     * DAO for release candidate source-of-truth access.
     */
    abstract fun releaseCandidateDao(): ReleaseCandidateDao
}
