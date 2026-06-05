package io.github.divyarajdev.architecturelab.core.domain.repository

import io.github.divyarajdev.architecturelab.core.model.ReleaseCandidate
import io.github.divyarajdev.architecturelab.core.model.ReleaseCandidateId
import kotlinx.coroutines.flow.Flow

/**
 * Domain boundary for release candidate data access.
 *
 * Implementations are provided by later data-layer milestones. This contract exposes domain models
 * only and must not leak DTOs, entities, framework APIs, network clients, database types, or
 * dependency injection annotations.
 */
interface ReleaseCandidateRepository {
    /**
     * Observes release candidates as domain models.
     *
     * @return stream of release candidates available to domain and presentation layers.
     */
    fun observeReleaseCandidates(): Flow<List<ReleaseCandidate>>

    /**
     * Resolves a release candidate by id.
     *
     * @param id release candidate identifier.
     * @return matching release candidate, or null when no candidate exists for [id].
     */
    suspend fun getReleaseCandidate(id: ReleaseCandidateId): ReleaseCandidate?

    /**
     * Refreshes release candidates from the configured data source.
     *
     * @return typed refresh outcome that does not expose transport, database, or exception types.
     */
    suspend fun refreshReleaseCandidates(): ReleaseCandidateRefreshResult
}
