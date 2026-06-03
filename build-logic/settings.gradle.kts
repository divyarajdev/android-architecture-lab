pluginManagement {
    val rootGradleProperties =
        java.util.Properties().apply {
            file("../gradle.properties").inputStream().use(::load)
        }
    val foojayResolverVersion =
        rootGradleProperties.getProperty("foojayResolverVersion")
            ?: error("foojayResolverVersion must be defined in root gradle.properties.")

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

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        google()
        mavenCentral()
    }

    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "architecturelab-build-logic"
