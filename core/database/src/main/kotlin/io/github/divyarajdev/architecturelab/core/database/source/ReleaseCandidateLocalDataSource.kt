package io.github.divyarajdev.architecturelab.core.database.source

import io.github.divyarajdev.architecturelab.core.database.model.ReleaseCandidateAggregate
import kotlinx.coroutines.flow.Flow

/**
 * Local data source boundary for release candidate source-of-truth access.
 */
interface ReleaseCandidateLocalDataSource {
    /**
     * Observes cached release candidates.
     */
    fun observeReleaseCandidates(): Flow<List<ReleaseCandidateAggregate>>

    /**
     * Returns one cached release candidate by id.
     */
    suspend fun getReleaseCandidate(id: String): ReleaseCandidateAggregate?

    /**
     * Replaces cached release candidate data.
     */
    suspend fun replaceReleaseCandidates(releaseCandidates: List<ReleaseCandidateAggregate>)
}
