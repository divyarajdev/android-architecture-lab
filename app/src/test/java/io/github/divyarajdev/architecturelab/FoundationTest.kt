package io.github.divyarajdev.architecturelab

import org.junit.Assert.assertEquals
import org.junit.Test

class FoundationTest {
    @Test
    fun applicationIdUsesGitHubOwnedNamespace() {
        assertEquals(
            "io.github.divyarajdev.architecturelab",
            BuildConfig.APPLICATION_ID,
        )
    }

    @Test
    fun versionNameMatchesFoundationRelease() {
        assertEquals(
            "0.1.0",
            BuildConfig.VERSION_NAME,
        )
    }
}
