package io.github.divyarajdev.architecturelab.core.network.source

import io.github.divyarajdev.architecturelab.core.network.model.NetworkModuleHealth
import io.github.divyarajdev.architecturelab.core.network.model.NetworkQualityGate
import io.github.divyarajdev.architecturelab.core.network.model.NetworkReleaseCandidate

/**
 * Deterministic fake remote source for portfolio-safe Release Readiness Lab data.
 */
class FakeReleaseCandidateNetworkDataSource : ReleaseCandidateNetworkDataSource {
    override suspend fun getReleaseCandidates(): List<NetworkReleaseCandidate> = listOf(
        NetworkReleaseCandidate(
            id = "release-2026.06.15",
            versionName = "2026.06.15",
            status = "IN_VALIDATION",
            qualityGates = listOf(
                NetworkQualityGate(
                    name = "Unit tests",
                    status = "PASSED",
                    isRequired = true,
                ),
                NetworkQualityGate(
                    name = "Android lint",
                    status = "PASSED",
                    isRequired = true,
                ),
            ),
            moduleHealth = listOf(
                NetworkModuleHealth(
                    moduleName = "core:data",
                    status = "HEALTHY",
                    openDefectCount = 0,
                ),
                NetworkModuleHealth(
                    moduleName = "core:database",
                    status = "HEALTHY",
                    openDefectCount = 0,
                ),
            ),
            updatedAt = 1_781_484_400_000,
        ),
    )
}
