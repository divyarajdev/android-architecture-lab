package io.github.divyarajdev.architecturelab.core.common

/**
 * UTC timestamp represented as epoch milliseconds.
 *
 * This type keeps time values explicit in domain models and avoids passing raw [Long] values across
 * module boundaries.
 *
 * @property value non-negative UTC epoch milliseconds.
 */
@JvmInline
value class UtcEpochMillis(
    val value: Long,
) {
    init {
        require(value >= 0) { "UTC epoch milliseconds must be non-negative." }
    }
}
