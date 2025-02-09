import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties
import org.gradle.api.provider.Provider

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.ktorfit)
    alias(libs.plugins.kotlin.serialization)
}

//ktorfit {
//    errorCheckingMode = ErrorCheckingMode.ERROR
//    generateQualifiedTypeName = false
//}

kotlin {
    tasks.matching { it.name == "syncComposeResourcesForIos" }
        .configureEach { enabled = false }
    targets.all {
        compilations.all {
            compilerOptions.configure {
                freeCompilerArgs.add("-Xexpect-actual-classes")
            }
        }
    }
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    fun isIncludeIos(providers: ProviderFactory): Boolean {
        val includeIosProvider: Provider<String> = providers.gradleProperty("include_ios")
        return if (includeIosProvider.isPresent) {
            includeIosProvider.get().toBoolean()
        } else {
            false // Default value if the property is not present
        }
    }// Example usage:
    val includeIos = isIncludeIos(providers)

    if (includeIos) {
        println("Including iOS")
        listOf(
            iosX64(),
            //        iosArm64(),
            //        iosSimulatorArm64()
        ).forEach { iosTarget ->
            iosTarget.binaries.framework {
                baseName = "ComposeApp"
                isStatic = true
                binaryOption("bundleId", "org.smartmuseum.fortnitecompanion.shared")
                binaryOption("bundleVersion", "1")
                export("dev.icerock.moko:resources:0.24.1")
                export("dev.icerock.moko:graphics:0.9.0") // toUIColor here
            }
        }
    } else {
        println("Not including iOS")
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.koin.core)
            implementation(compose.preview)
            implementation(libs.compose.runtime)
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.lifecycle.viewmodel.compose)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.moko.resources)
            implementation(libs.koin.core)
            implementation(libs.koin.android)
            // Ktor CIO Engine (Android/JVM)
            implementation(libs.ktor.client.cio)
        }
        iosMain.dependencies {
            // Ktor Darwin Engine (iOS)
            implementation(libs.ktor.client.darwin)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
//            implementation(libs.androidx.lifecycle.runtime.compose)
            //Ktorfit
            implementation(libs.ktorfit.lib)
            implementation(libs.ktorfit.converters.call)
            implementation(libs.ktorfit.converters.flow)
            implementation(libs.ktorfit.lib)
            //Coroutines
            implementation(libs.kotlinx.coroutines.core)
            //Koin
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.koin.compose.viewmodel.navigation)
            //K-Tor
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.serialization)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.logging)
            implementation(libs.kotlinx.serialization)
            implementation(libs.kotlinx.serialization.json)
            //DateTime
            api(libs.kotlinx.datetime)
            //logging
            api(libs.logging)
            //konnectivity
            implementation(libs.konnectivity)
            //Moko
            api(libs.moko.resources)
            api(libs.moko.resources.compose)
            //Stately
            implementation(libs.stately.common)
            //Coil
            implementation(libs.coil.compose)
            implementation(libs.coil.network.okhttp)
            //YoutubeComposable
            implementation(libs.compose.multiplatform.media.player)
        }
    }
}

android {
    namespace = "org.smartmuseum.fortnitecompanion"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "org.smartmuseum.fortnitecompanion"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    val localProperties = Properties().apply {
        val localPropsFile = rootProject.file("local.properties")
        if (localPropsFile.exists()) {
            load(localPropsFile.inputStream())
        }
    }
    // Define buildConfigFields
    buildTypes.forEach { buildType ->
        val apiKey = localProperties.getProperty("FORTNITE_API_KEY")
        if (apiKey != null) {
            buildType.buildConfigField("String", "FORTNITE_API_KEY", "\"$apiKey\"")
        } else {
            println("Warning: FORTNITE_API_KEY not found in local.properties")
        }
    }
}

val ktorfitVersion: String = libs.versions.ktorfit.lib.get()

dependencies {
    debugImplementation(compose.uiTooling)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.tooling.preview)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

