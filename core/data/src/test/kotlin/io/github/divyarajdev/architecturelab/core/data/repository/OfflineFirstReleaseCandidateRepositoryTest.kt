package io.github.divyarajdev.architecturelab.core.data.repository

import io.github.divyarajdev.architecturelab.core.data.testing.DataFixtures.RELEASE_CANDIDATE_ID
import io.github.divyarajdev.architecturelab.core.data.testing.DataFixtures.domainReleaseCandidate
import io.github.divyarajdev.architecturelab.core.data.testing.DataFixtures.localAggregate
import io.github.divyarajdev.architecturelab.core.data.testing.DataFixtures.networkReleaseCandidate
import io.github.divyarajdev.architecturelab.core.database.model.ReleaseCandidateAggregate
import io.github.divyarajdev.architecturelab.core.database.source.ReleaseCandidateLocalDataSource
import io.github.divyarajdev.architecturelab.core.domain.repository.ReleaseCandidateRefreshFailureReason
import io.github.divyarajdev.architecturelab.core.domain.repository.ReleaseCandidateRefreshResult
import io.github.divyarajdev.architecturelab.core.model.ReleaseCandidateId
import io.github.divyarajdev.architecturelab.core.network.model.NetworkReleaseCandidate
import io.github.divyarajdev.architecturelab.core.network.source.ReleaseCandidateNetworkDataSource
import io.github.divyarajdev.architecturelab.core.network.source.ReleaseCandidateNetworkException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class OfflineFirstReleaseCandidateRepositoryTest {
    @Test
    fun `observes cached release candidates as domain models`() = runTest {
        val repository = repository(
            localDataSource = FakeLocalDataSource(listOf(localAggregate())),
        )

        val result = repository.observeReleaseCandidates().first()

        assertEquals(listOf(domainReleaseCandidate()), result)
    }

    @Test
    fun `get release candidate returns mapped domain model by id`() = runTest {
        val repository = repository(
            localDataSource = FakeLocalDataSource(listOf(localAggregate())),
        )

        val result = repository.getReleaseCandidate(ReleaseCandidateId(RELEASE_CANDIDATE_ID))

        assertEquals(domainReleaseCandidate(), result)
    }

    @Test
    fun `successful refresh writes remote data into local source`() = runTest {
        val localDataSource = FakeLocalDataSource()
        val repository = repository(
            localDataSource = localDataSource,
            networkDataSource = FakeNetworkDataSource(listOf(networkReleaseCandidate())),
        )

        val refreshResult = repository.refreshReleaseCandidates()
        val cachedResult = repository.observeReleaseCandidates().first()

        assertEquals(ReleaseCandidateRefreshResult.Success, refreshResult)
        assertEquals(listOf(localAggregate()), localDataSource.releaseCandidates.value)
        assertEquals(listOf(domainReleaseCandidate()), cachedResult)
    }

    @Test
    fun `failed refresh preserves cached data`() = runTest {
        val localDataSource = FakeLocalDataSource(listOf(localAggregate()))
        val repository = repository(
            localDataSource = localDataSource,
            networkDataSource = FailingNetworkDataSource,
        )

        val refreshResult = repository.refreshReleaseCandidates()
        val cachedResult = repository.observeReleaseCandidates().first()

        assertEquals(
            ReleaseCandidateRefreshResult.Failure(
                reason = ReleaseCandidateRefreshFailureReason.REMOTE_SOURCE_UNAVAILABLE,
            ),
            refreshResult,
        )
        assertEquals(listOf(localAggregate()), localDataSource.releaseCandidates.value)
        assertEquals(listOf(domainReleaseCandidate()), cachedResult)
    }

    private fun repository(
        localDataSource: ReleaseCandidateLocalDataSource = FakeLocalDataSource(),
        networkDataSource: ReleaseCandidateNetworkDataSource = FakeNetworkDataSource(),
    ): OfflineFirstReleaseCandidateRepository = OfflineFirstReleaseCandidateRepository(
        localDataSource = localDataSource,
        networkDataSource = networkDataSource,
    )

    private class FakeLocalDataSource(
        initialReleaseCandidates: List<ReleaseCandidateAggregate> = emptyList(),
    ) : ReleaseCandidateLocalDataSource {
        val releaseCandidates = MutableStateFlow(initialReleaseCandidates)

        override fun observeReleaseCandidates(): Flow<List<ReleaseCandidateAggregate>> = releaseCandidates

        override suspend fun getReleaseCandidate(id: String): ReleaseCandidateAggregate? = releaseCandidates.value.firstOrNull { releaseCandidate ->
            releaseCandidate.releaseCandidate.id == id
        }

        override suspend fun replaceReleaseCandidates(
            releaseCandidates: List<ReleaseCandidateAggregate>,
        ) {
            this.releaseCandidates.value = releaseCandidates
        }
    }

    private class FakeNetworkDataSource(
        private val releaseCandidates: List<NetworkReleaseCandidate> = emptyList(),
    ) : ReleaseCandidateNetworkDataSource {
        override suspend fun getReleaseCandidates(): List<NetworkReleaseCandidate> = releaseCandidates
    }

    private data object FailingNetworkDataSource : ReleaseCandidateNetworkDataSource {
        override suspend fun getReleaseCandidates(): List<NetworkReleaseCandidate> = throw ReleaseCandidateNetworkException("Remote source unavailable.")
    }
}
