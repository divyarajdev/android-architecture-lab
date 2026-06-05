package io.github.divyarajdev.architecturelab.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import io.github.divyarajdev.architecturelab.core.database.model.ModuleHealthEntity
import io.github.divyarajdev.architecturelab.core.database.model.QualityGateEntity
import io.github.divyarajdev.architecturelab.core.database.model.ReleaseCandidateAggregate
import io.github.divyarajdev.architecturelab.core.database.model.ReleaseCandidateEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO for release candidate local source-of-truth access.
 */
@Dao
abstract class ReleaseCandidateDao {
    /**
     * Observes release candidates with quality gates and module health.
     */
    @Transaction
    @Query("SELECT * FROM release_candidates ORDER BY updated_at DESC")
    abstract fun observeReleaseCandidates(): Flow<List<ReleaseCandidateAggregate>>

    /**
     * Returns a release candidate by id.
     */
    @Transaction
    @Query("SELECT * FROM release_candidates WHERE id = :id")
    abstract suspend fun getReleaseCandidate(id: String): ReleaseCandidateAggregate?

    /**
     * Replaces all cached release candidate data in one database transaction.
     */
    @Transaction
    open suspend fun replaceReleaseCandidates(releaseCandidates: List<ReleaseCandidateAggregate>) {
        clearModuleHealth()
        clearQualityGates()
        clearReleaseCandidates()
        insertReleaseCandidates(releaseCandidates.map(ReleaseCandidateAggregate::releaseCandidate))
        insertQualityGates(releaseCandidates.flatMap(ReleaseCandidateAggregate::qualityGates))
        insertModuleHealth(releaseCandidates.flatMap(ReleaseCandidateAggregate::moduleHealth))
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract suspend fun insertReleaseCandidates(
        releaseCandidates: List<ReleaseCandidateEntity>,
    )

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract suspend fun insertQualityGates(qualityGates: List<QualityGateEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract suspend fun insertModuleHealth(moduleHealth: List<ModuleHealthEntity>)

    @Query("DELETE FROM module_health")
    protected abstract suspend fun clearModuleHealth()

    @Query("DELETE FROM quality_gates")
    protected abstract suspend fun clearQualityGates()

    @Query("DELETE FROM release_candidates")
    protected abstract suspend fun clearReleaseCandidates()
}
