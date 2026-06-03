package io.github.divyarajdev.architecturelab.core.model

/**
 * Domain validation gate used to evaluate release readiness.
 *
 * @property name non-blank quality gate name.
 * @property status latest known quality gate status.
 * @property isRequired true when the gate can block release readiness.
 */
data class QualityGate(
    val name: String,
    val status: QualityGateStatus,
    val isRequired: Boolean,
) {
    init {
        require(name.isNotBlank()) { "Quality gate name must not be blank." }
    }
}

/**
 * Validation state for a [QualityGate].
 */
enum class QualityGateStatus {
    PASSED,
    WARNING,
    FAILED,
    NOT_RUN,
}
