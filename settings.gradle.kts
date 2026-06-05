pluginManagement {
    val foojayResolverVersion: String by settings

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    plugins {
        id("org.gradle.toolchains.foojay-resolver-convention") version foojayResolverVersion
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention")
}

includeBuild("build-logic")

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "android-architecture-lab"

include(":app")
include(":core:common")
include(":core:data")
include(":core:database")
include(":core:model")
include(":core:domain")
include(":core:network")
