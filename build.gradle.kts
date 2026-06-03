import com.diffplug.spotless.LineEnding

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.spotless)
}

spotless {
    lineEndings = LineEnding.UNIX

    kotlin {
        target("**/*.kt")
        targetExclude("**/build/**/*.kt")
        ktlint(libs.versions.ktlint.get()).editorConfigOverride(
            mapOf<String, Any>(
                "ktlint_function_naming_ignore_when_annotated_with" to "Composable",
            ),
        )
    }

    kotlinGradle {
        target("**/*.gradle.kts")
        ktlint(libs.versions.ktlint.get())
    }

    format("misc") {
        target("**/*.md", "**/*.yml", "**/*.yaml", "**/.gitignore", "**/.editorconfig")
        trimTrailingWhitespace()
        endWithNewline()
    }
}
