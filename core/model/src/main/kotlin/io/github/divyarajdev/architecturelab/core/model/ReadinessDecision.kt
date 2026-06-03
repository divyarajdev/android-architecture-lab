package io.github.divyarajdev.architecturelab.core.model

/**
 * Domain decision produced after evaluating a release candidate.
 */
sealed interface ReadinessDecision {
    /**
     * Release candidate has no blocking or attention reasons.
     */
    data object Ready : ReadinessDecision

    /**
     * Release candidate can continue, but requires review before release.
     *
     * @property reasons non-empty list of review reasons.
     */
    data class NeedsAttention(
        val reasons: List<ReadinessReason>,
    ) : ReadinessDecision {
        init {
            require(reasons.isNotEmpty()) { "NeedsAttention requires at least one reason." }
        }
    }

    /**
     * Release candidate must not proceed until blocking reasons are resolved.
     *
     * @property reasons non-empty list of blocking reasons.
     */
    data class Blocked(
        val reasons: List<ReadinessReason>,
    ) : ReadinessDecision {
        init {
            require(reasons.isNotEmpty()) { "Blocked requires at least one reason." }
        }
    }
}

/**
 * Typed domain reason for a [ReadinessDecision].
 *
 * Reasons intentionally avoid UI text so presentation layers can map them to localized copy.
 */
sealed interface ReadinessReason {
    /**
     * Release candidate is explicitly blocked.
     */
    data object ReleaseCandidateBlocked : ReadinessReason

    /**
     * Required quality gate failed.
     *
     * @property qualityGateName non-blank quality gate name.
     */
    data class RequiredQualityGateFailed(
        val qualityGateName: String,
    ) : ReadinessReason {
        init {
            require(qualityGateName.isNotBlank()) { "Quality gate name must not be blank." }
        }
    }

    /**
     * Module health is blocked.
     *
     * @property moduleName non-blank module name.
     */
    data class ModuleBlocked(
        val moduleName: String,
    ) : ReadinessReason {
        init {
            require(moduleName.isNotBlank()) { "Module name must not be blank." }
        }
    }

    /**
     * Release candidate has no quality gate evidence.
     */
    data object QualityGateEvidenceMissing : ReadinessReason

    /**
     * Quality gate completed with a warning status.
     *
     * @property qualityGateName non-blank quality gate name.
     */
    data class QualityGateWarning(
        val qualityGateName: String,
    ) : ReadinessReason {
        init {
            require(qualityGateName.isNotBlank()) { "Quality gate name must not be blank." }
        }
    }

    /**
     * Required quality gate has not run.
     *
     * @property qualityGateName non-blank quality gate name.
     */
    data class RequiredQualityGateNotRun(
        val qualityGateName: String,
    ) : ReadinessReason {
        init {
            require(qualityGateName.isNotBlank()) { "Quality gate name must not be blank." }
        }
    }

    /**
     * Module health is degraded.
     *
     * @property moduleName non-blank module name.
     */
    data class ModuleDegraded(
        val moduleName: String,
    ) : ReadinessReason {
        init {
            require(moduleName.isNotBlank()) { "Module name must not be blank." }
        }
    }

    /**
     * Module health has not been evaluated.
     *
     * @property moduleName non-blank module name.
     */
    data class ModuleNotEvaluated(
        val moduleName: String,
    ) : ReadinessReason {
        init {
            require(moduleName.isNotBlank()) { "Module name must not be blank." }
        }
    }
}
