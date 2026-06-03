package io.github.divyarajdev.architecturelab.core.domain.usecase

import io.github.divyarajdev.architecturelab.core.domain.repository.ReleaseCandidateRepository
import io.github.divyarajdev.architecturelab.core.model.ReleaseCandidate
import kotlinx.coroutines.flow.Flow

/**
 * Observes release candidates through the domain repository contract.
 *
 * @param repository repository boundary that supplies release candidate domain models.
 */
class ObserveReleaseCandidatesUseCase(
    private val repository: ReleaseCandidateRepository,
) {
    /**
     * Starts observing release candidates.
     *
     * @return stream of release candidate domain models.
     */
    operator fun invoke(): Flow<List<ReleaseCandidate>> = repository.observeReleaseCandidates()
}
