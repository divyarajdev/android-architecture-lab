package io.github.divyarajdev.architecturelab.buildlogic

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

/**
 * Applies shared Android library configuration for core Android modules.
 *
 * The convention centralizes SDK versions, namespace derivation, and Java compatibility so Android
 * library modules do not repeat build configuration.
 */
class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.library")

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            val namespaceRoot = providers.gradleProperty("app.namespaceRoot").get()
            val projectNamespace = providers.gradleProperty("app.projectNamespace").get()
            val javaVersionName = libs.findVersion("java").get().requiredVersion
            val javaVersion = JavaVersion.toVersion(javaVersionName.toInt())
            val moduleNamespace = path
                .removePrefix(":")
                .replace(":", ".")
                .replace("-", "")

            extensions.configure<LibraryExtension> {
                namespace = "$namespaceRoot.$projectNamespace.$moduleNamespace"
                compileSdk = libs.findVersion("compileSdk").get().requiredVersion.toInt()

                defaultConfig {
                    minSdk = libs.findVersion("minSdk").get().requiredVersion.toInt()
                }

                compileOptions {
                    sourceCompatibility = javaVersion
                    targetCompatibility = javaVersion
                }
            }

            dependencies {
                add("testImplementation", libs.findLibrary("junit").get().get())
            }
        }
    }
}
