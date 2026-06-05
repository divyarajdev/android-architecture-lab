package io.github.divyarajdev.architecturelab.core.network.model

/**
 * Remote DTO for release candidate data.
 */
data class NetworkReleaseCandidate(
    val id: String,
    val versionName: String,
    val status: String,
    val qualityGates: List<NetworkQualityGate>,
    val moduleHealth: List<NetworkModuleHealth>,
    val updatedAt: Long,
)

/**
 * Remote DTO for quality gate data.
 */
data class NetworkQualityGate(
    val name: String,
    val status: String,
    val isRequired: Boolean,
)

/**
 * Remote DTO for module health data.
 */
data class NetworkModuleHealth(
    val moduleName: String,
    val status: String,
    val openDefectCount: Int,
)
