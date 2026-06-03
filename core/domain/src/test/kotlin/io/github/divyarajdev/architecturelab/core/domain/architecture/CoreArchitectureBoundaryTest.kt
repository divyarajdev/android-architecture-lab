package io.github.divyarajdev.architecturelab.core.domain.architecture

import io.github.divyarajdev.architecturelab.core.domain.repository.ReleaseCandidateRepository
import org.junit.Assert.assertTrue
import org.junit.Test

class CoreArchitectureBoundaryTest {
    @Test
    fun `repository contract does not expose framework or implementation types`() {
        val exposedTypeNames = ReleaseCandidateRepository::class.java.declaredMethods
            .flatMap { method ->
                buildList {
                    add(method.genericReturnType.typeName)
                    method.genericParameterTypes.forEach { parameterType ->
                        add(parameterType.typeName)
                    }
                }
            }

        val forbiddenTypeNames = listOf(
            "android.",
            "androidx.",
            "androidx.room.",
            "dagger.",
            "javax.inject.",
            "okhttp3.",
            "retrofit2.",
        )

        assertTrue(
            exposedTypeNames.none { typeName ->
                forbiddenTypeNames.any { forbiddenTypeName ->
                    typeName.contains(forbiddenTypeName)
                }
            },
        )
    }
}
