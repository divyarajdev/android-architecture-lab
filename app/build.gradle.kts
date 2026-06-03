import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

val namespaceRoot: String = providers.gradleProperty("app.namespaceRoot").get()
val projectNamespace: String = providers.gradleProperty("app.projectNamespace").get()
val appNamespace: String = "$namespaceRoot.$projectNamespace"
val appVersionCode: Int = providers.gradleProperty("app.versionCode").get().toInt()
val appVersionName: String = providers.gradleProperty("app.versionName").get()
val appTestInstrumentationRunner: String =
    providers.gradleProperty("app.testInstrumentationRunner").get()
val javaVersionName: String = libs.versions.java.get()
val javaToolchainVersion: Int = javaVersionName.toInt()
val javaVersion: JavaVersion = JavaVersion.toVersion(javaToolchainVersion)
val configuredCompileSdk: Int =
    libs.versions.compileSdk
        .get()
        .toInt()
val configuredMinSdk: Int =
    libs.versions.minSdk
        .get()
        .toInt()
val configuredTargetSdk: Int =
    libs.versions.targetSdk
        .get()
        .toInt()

android {
    namespace = appNamespace
    compileSdk = configuredCompileSdk

    defaultConfig {
        applicationId = appNamespace
        minSdk = configuredMinSdk
        targetSdk = configuredTargetSdk
        versionCode = appVersionCode
        versionName = appVersionName

        testInstrumentationRunner = appTestInstrumentationRunner
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }

    compileOptions {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

kotlin {
    jvmToolchain(javaToolchainVersion)
    compilerOptions {
        jvmTarget.set(JvmTarget.fromTarget(javaVersionName))
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(platform(libs.androidx.compose.bom))

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
}
