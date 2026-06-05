package io.github.divyarajdev.architecturelab.core.network.source

import io.github.divyarajdev.architecturelab.core.network.model.NetworkReleaseCandidate

/**
 * Remote data source boundary for release candidate data.
 */
interface ReleaseCandidateNetworkDataSource {
    /**
     * Returns release candidates from the configured remote source.
     */
    suspend fun getReleaseCandidates(): List<NetworkReleaseCandidate>
}
