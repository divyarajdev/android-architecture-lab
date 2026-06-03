package io.github.divyarajdev.architecturelab.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

/**
 * Applies shared Kotlin JVM module configuration for non-Android core modules.
 *
 * The convention centralizes Java toolchain selection, Kotlin JVM target, and JUnit test execution
 * so core modules use the same build behavior.
 */
class KotlinLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.jvm")

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            val javaVersionName = libs.findVersion("java").get().requiredVersion
            val javaToolchainVersion = javaVersionName.toInt()

            extensions.configure<KotlinJvmProjectExtension> {
                jvmToolchain(javaToolchainVersion)
                compilerOptions {
                    jvmTarget.set(JvmTarget.fromTarget(javaVersionName))
                }
            }

            dependencies {
                add("testImplementation", libs.findLibrary("junit").get().get())
            }

            tasks.withType<Test>().configureEach {
                useJUnit()
            }
        }
    }
}
