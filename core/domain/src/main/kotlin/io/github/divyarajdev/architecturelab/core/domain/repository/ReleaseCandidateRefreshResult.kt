package io.github.divyarajdev.architecturelab.core.domain.repository

/**
 * Domain refresh outcome for release candidate data.
 */
sealed interface ReleaseCandidateRefreshResult {
    /**
     * Refresh completed and the source of truth was updated.
     */
    data object Success : ReleaseCandidateRefreshResult

    /**
     * Refresh failed without exposing implementation exceptions across the domain boundary.
     *
     * @property reason typed failure reason for domain and presentation layers.
     */
    data class Failure(
        val reason: ReleaseCandidateRefreshFailureReason,
    ) : ReleaseCandidateRefreshResult
}

/**
 * Domain-safe refresh failure reason.
 */
enum class ReleaseCandidateRefreshFailureReason {
    REMOTE_SOURCE_UNAVAILABLE,
}
