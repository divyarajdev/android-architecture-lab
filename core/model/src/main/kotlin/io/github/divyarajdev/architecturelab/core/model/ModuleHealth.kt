package io.github.divyarajdev.architecturelab.core.model

/**
 * Domain health signal for a release-owned module.
 *
 * @property moduleName non-blank module identifier.
 * @property status latest known module health status.
 * @property openDefectCount number of open defects associated with this module.
 */
data class ModuleHealth(
    val moduleName: String,
    val status: ModuleHealthStatus,
    val openDefectCount: Int,
) {
    init {
        require(moduleName.isNotBlank()) { "Module name must not be blank." }
        require(openDefectCount >= 0) { "Open defect count must not be negative." }
    }
}

/**
 * Module-level health state used by release readiness evaluation.
 */
enum class ModuleHealthStatus {
    HEALTHY,
    DEGRADED,
    BLOCKED,
    NOT_EVALUATED,
}
