package io.github.divyarajdev.architecturelab.core.data.repository

import io.github.divyarajdev.architecturelab.core.data.mapper.toDomain
import io.github.divyarajdev.architecturelab.core.data.mapper.toLocalAggregate
import io.github.divyarajdev.architecturelab.core.database.source.ReleaseCandidateLocalDataSource
import io.github.divyarajdev.architecturelab.core.domain.repository.ReleaseCandidateRefreshFailureReason
import io.github.divyarajdev.architecturelab.core.domain.repository.ReleaseCandidateRefreshResult
import io.github.divyarajdev.architecturelab.core.domain.repository.ReleaseCandidateRepository
import io.github.divyarajdev.architecturelab.core.model.ReleaseCandidate
import io.github.divyarajdev.architecturelab.core.model.ReleaseCandidateId
import io.github.divyarajdev.architecturelab.core.network.source.ReleaseCandidateNetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Cache-first implementation of [ReleaseCandidateRepository].
 *
 * The repository exposes local data as the source of truth and uses the remote source only to
 * refresh local state.
 */
class OfflineFirstReleaseCandidateRepository(
    private val localDataSource: ReleaseCandidateLocalDataSource,
    private val networkDataSource: ReleaseCandidateNetworkDataSource,
) : ReleaseCandidateRepository {
    override fun observeReleaseCandidates(): Flow<List<ReleaseCandidate>> = localDataSource.observeReleaseCandidates()
        .map { releaseCandidates ->
            releaseCandidates.map { releaseCandidate -> releaseCandidate.toDomain() }
        }

    override suspend fun getReleaseCandidate(id: ReleaseCandidateId): ReleaseCandidate? = localDataSource.getReleaseCandidate(id.value)?.toDomain()

    override suspend fun refreshReleaseCandidates(): ReleaseCandidateRefreshResult = runCatching {
        val releaseCandidates = networkDataSource.getReleaseCandidates()
            .map { releaseCandidate -> releaseCandidate.toLocalAggregate() }

        localDataSource.replaceReleaseCandidates(releaseCandidates)
    }.fold(
        onSuccess = { ReleaseCandidateRefreshResult.Success },
        onFailure = {
            ReleaseCandidateRefreshResult.Failure(
                reason = ReleaseCandidateRefreshFailureReason.REMOTE_SOURCE_UNAVAILABLE,
            )
        },
    )
}
