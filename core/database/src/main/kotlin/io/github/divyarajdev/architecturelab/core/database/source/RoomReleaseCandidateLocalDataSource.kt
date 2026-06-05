package io.github.divyarajdev.architecturelab.core.database.source

import io.github.divyarajdev.architecturelab.core.database.dao.ReleaseCandidateDao
import io.github.divyarajdev.architecturelab.core.database.model.ReleaseCandidateAggregate
import kotlinx.coroutines.flow.Flow

/**
 * Room-backed implementation of [ReleaseCandidateLocalDataSource].
 */
class RoomReleaseCandidateLocalDataSource(
    private val releaseCandidateDao: ReleaseCandidateDao,
) : ReleaseCandidateLocalDataSource {
    override fun observeReleaseCandidates(): Flow<List<ReleaseCandidateAggregate>> = releaseCandidateDao.observeReleaseCandidates()

    override suspend fun getReleaseCandidate(id: String): ReleaseCandidateAggregate? = releaseCandidateDao.getReleaseCandidate(id)

    override suspend fun replaceReleaseCandidates(
        releaseCandidates: List<ReleaseCandidateAggregate>,
    ) {
        releaseCandidateDao.replaceReleaseCandidates(releaseCandidates)
    }
}
