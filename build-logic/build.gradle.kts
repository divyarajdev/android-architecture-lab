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
        register("androidLibraryConvention") {
            id = "architecturelab.android.library"
            implementationClass =
                "io.github.divyarajdev.architecturelab.buildlogic.AndroidLibraryConventionPlugin"
        }
        register("kotlinLibraryConvention") {
            id = "architecturelab.kotlin.library"
            implementationClass =
                "io.github.divyarajdev.architecturelab.buildlogic.KotlinLibraryConventionPlugin"
        }
        register("roomConvention") {
            id = "architecturelab.room"
            implementationClass =
                "io.github.divyarajdev.architecturelab.buildlogic.RoomConventionPlugin"
        }
    }
}

dependencies {
    implementation(libs.android.gradle.plugin)
    implementation(libs.androidx.room.gradle.plugin)
    implementation(libs.ksp.gradle.plugin)
    implementation(libs.kotlin.gradle.plugin)
}
