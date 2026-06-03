package io.github.divyarajdev.architecturelab.core.domain.usecase

import io.github.divyarajdev.architecturelab.core.domain.repository.ReleaseCandidateRepository
import io.github.divyarajdev.architecturelab.core.model.ReleaseCandidate
import io.github.divyarajdev.architecturelab.core.model.ReleaseCandidateId

/**
 * Resolves one release candidate through the domain repository contract.
 *
 * @param repository repository boundary that supplies release candidate domain models.
 */
class GetReleaseCandidateUseCase(
    private val repository: ReleaseCandidateRepository,
) {
    /**
     * Returns a release candidate by id.
     *
     * @param id release candidate identifier.
     * @return matching release candidate, or null when the candidate does not exist.
     */
    suspend operator fun invoke(id: ReleaseCandidateId): ReleaseCandidate? =
        repository.getReleaseCandidate(id)
}
