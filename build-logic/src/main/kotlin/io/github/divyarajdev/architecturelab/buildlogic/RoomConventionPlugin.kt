package io.github.divyarajdev.architecturelab.buildlogic

import androidx.room.gradle.RoomExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

/**
 * Applies Room configuration for modules that own local persistence.
 *
 * The convention keeps Room runtime, KTX, compiler, KSP, and schema location centralized.
 */
class RoomConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("androidx.room")
            pluginManager.apply("com.google.devtools.ksp")

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            extensions.configure<RoomExtension> {
                schemaDirectory(layout.projectDirectory.dir("schemas").asFile.path)
            }

            dependencies {
                add("implementation", libs.findLibrary("androidx.room.runtime").get().get())
                add("implementation", libs.findLibrary("androidx.room.ktx").get().get())
                add("ksp", libs.findLibrary("androidx.room.compiler").get().get())
            }
        }
    }
}
