package io.github.divyarajdev.architecturelab.core.network.source

/**
 * Fake remote source failure used by tests and controlled failure scenarios.
 */
class ReleaseCandidateNetworkException(
    message: String,
) : RuntimeException(message)
