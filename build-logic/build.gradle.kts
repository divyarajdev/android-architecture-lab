plugins {
    `kotlin-dsl`
}

val javaToolchainVersion: Int =
    libs.versions.java
        .get()
        .toInt()

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(javaToolchainVersion))
    }
}

gradlePlugin {
    plugins {
        register("kotlinLibraryConvention") {
            id = "architecturelab.kotlin.library"
            implementationClass =
                "io.github.divyarajdev.architecturelab.buildlogic.KotlinLibraryConventionPlugin"
        }
    }
}

dependencies {
    implementation(libs.kotlin.gradle.plugin)
}
