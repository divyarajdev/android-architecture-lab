package io.github.divyarajdev.architecturelab.core.domain.usecase

import io.github.divyarajdev.architecturelab.core.domain.repository.ReleaseCandidateRefreshResult
import io.github.divyarajdev.architecturelab.core.domain.repository.ReleaseCandidateRepository

/**
 * Refreshes release candidate data through the domain repository contract.
 *
 * The use case keeps refresh orchestration callable from later ViewModels without exposing data
 * source implementation details.
 *
 * @property releaseCandidateRepository domain repository contract.
 */
class RefreshReleaseCandidatesUseCase(
    private val releaseCandidateRepository: ReleaseCandidateRepository,
) {
    /**
     * Requests a repository refresh.
     *
     * @return domain-safe refresh result.
     */
    suspend operator fun invoke(): ReleaseCandidateRefreshResult = releaseCandidateRepository.refreshReleaseCandidates()
}
