package io.github.divyarajdev.architecturelab.core.data.mapper

import io.github.divyarajdev.architecturelab.core.data.testing.DataFixtures.domainReleaseCandidate
import io.github.divyarajdev.architecturelab.core.data.testing.DataFixtures.localAggregate
import io.github.divyarajdev.architecturelab.core.data.testing.DataFixtures.networkReleaseCandidate
import org.junit.Assert.assertEquals
import org.junit.Test

class ReleaseCandidateMappersTest {
    @Test
    fun `maps local aggregate to domain model`() {
        val result = localAggregate().toDomain()

        assertEquals(domainReleaseCandidate(), result)
    }

    @Test
    fun `maps remote dto to local aggregate`() {
        val result = networkReleaseCandidate().toLocalAggregate()

        assertEquals(localAggregate(), result)
    }
}
