package io.github.divyarajdev.architecturelab.core.domain.usecase

import io.github.divyarajdev.architecturelab.core.domain.repository.ReleaseCandidateRepository
import io.github.divyarajdev.architecturelab.core.domain.testing.ReleaseCandidateFixtures.DOMAIN_MODULE_NAME
import io.github.divyarajdev.architecturelab.core.domain.testing.ReleaseCandidateFixtures.healthyModule
import io.github.divyarajdev.architecturelab.core.domain.testing.ReleaseCandidateFixtures.releaseCandidate
import io.github.divyarajdev.architecturelab.core.model.ReleaseCandidate
import io.github.divyarajdev.architecturelab.core.model.ReleaseCandidateId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class ReleaseCandidateUseCaseTest {
    private val releaseCandidate = releaseCandidate(
        moduleHealth = listOf(healthyModule(DOMAIN_MODULE_NAME)),
    )

    private val repository = FakeReleaseCandidateRepository(listOf(releaseCandidate))

    @Test
    fun `observe use case returns repository-backed domain models`() = runBlocking {
        val result = ObserveReleaseCandidatesUseCase(repository)().first()

        assertEquals(listOf(releaseCandidate), result)
    }

    @Test
    fun `get use case returns matching release candidate`() = runBlocking {
        val result = GetReleaseCandidateUseCase(repository)(releaseCandidate.id)

        assertEquals(releaseCandidate, result)
    }

    private class FakeReleaseCandidateRepository(
        private val releaseCandidates: List<ReleaseCandidate>,
    ) : ReleaseCandidateRepository {
        override fun observeReleaseCandidates(): Flow<List<ReleaseCandidate>> =
            flowOf(releaseCandidates)

        override suspend fun getReleaseCandidate(id: ReleaseCandidateId): ReleaseCandidate? =
            releaseCandidates.firstOrNull { releaseCandidate -> releaseCandidate.id == id }
    }
}
