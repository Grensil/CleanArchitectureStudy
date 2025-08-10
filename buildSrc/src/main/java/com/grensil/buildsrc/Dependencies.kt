object Versions {
    // Kotlin
    const val kotlin = "1.8.0"
    const val coroutines = "1.6.4"
    const val ksp = "1.8.0-1.0.9" // KSP 추가

    // Android
    const val compileSdk = 35
    const val minSdk = 24
    const val targetSdk = 35
    const val versionCode = 1
    const val versionName = "1.0"

    // AndroidX
    const val coreKtx = "1.9.0"
    const val appCompat = "1.5.1"
    const val activityKtx = "1.6.0"
    const val lifecycleKtx = "2.6.2"
    const val preference = "1.2.0"

    // Compose
    const val composeBom = "2023.10.01"
    const val composeActivity = "1.8.0"
    const val composeLifecycle = "2.6.2"

    // DI
    const val hilt = "2.48"
    const val hiltNavigation = "1.1.0"

    // Network
    const val retrofit = "2.9.0"
    const val okhttp = "4.11.0"

    // Image Loading
    const val coil = "2.5.0"

    // Navigation
    const val navigation = "2.7.4"
    const val accompanistNavigation = "0.31.1-alpha"

    //Room
    const val room = "2.6.0"

    // Storage
    const val datastore = "1.0.0"

    // Material
    const val material = "1.10.0"

    // Testing
    const val junit = "4.13.2"
    const val junitExt = "1.1.5"
    const val espresso = "3.5.1"
    const val mockk = "1.13.8"
    const val coreTesting = "2.2.0"

    // Plugin Versions
    const val androidGradle = "8.1.2"
    const val kotlinPlugin = "1.8.0"
    const val hiltPlugin = "2.48"
    const val kspPlugin = "1.8.0-1.0.9" // KSP 플러그인 추가
}

object Dependencies {

    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtx}"
        const val lifecycleViewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleKtx}"
        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleKtx}"
        const val preference = "androidx.preference:preference-ktx:${Versions.preference}"
        const val datastore = "androidx.datastore:datastore-preferences:${Versions.datastore}"
    }

    object Compose {
        const val bom = "androidx.compose:compose-bom:${Versions.composeBom}"
        const val ui = "androidx.compose.ui:ui"
        const val uiGraphics = "androidx.compose.ui:ui-graphics"
        const val uiTooling = "androidx.compose.ui:ui-tooling"
        const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
        const val uiTestManifest = "androidx.compose.ui:ui-test-manifest"
        const val material = "androidx.compose.material:material"
        const val material3 = "androidx.compose.material3:material3"
        const val materialIconsExtended = "androidx.compose.material:material-icons-extended"
        const val animation = "androidx.compose.animation:animation"
        const val activity = "androidx.activity:activity-compose:${Versions.composeActivity}"
        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-compose:${Versions.composeLifecycle}"
    }

    object Hilt {
        const val android = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val compiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
        const val navigationCompose = "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigation}"
    }

    object Navigation {
        const val compose = "androidx.navigation:navigation-compose:${Versions.navigation}"
        const val ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    }

    object Room {
        const val runtime = "androidx.room:room-runtime:${Versions.room}"
        const val ktx = "androidx.room:room-ktx:${Versions.room}"
        const val compiler = "androidx.room:room-compiler:${Versions.room}"
    }

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
        const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
        const val okhttpUrlConnection = "com.squareup.okhttp3:okhttp-urlconnection:${Versions.okhttp}"
    }

    object Image {
        const val coil = "io.coil-kt:coil:${Versions.coil}"
        const val coilCompose = "io.coil-kt:coil-compose:${Versions.coil}"
        const val coilSvg = "io.coil-kt:coil-svg:${Versions.coil}"
    }

    object Material {
        const val material = "com.google.android.material:material:${Versions.material}"
    }

    object Accompanist {
        const val navigationAnimation = "com.google.accompanist:accompanist-navigation-animation:${Versions.accompanistNavigation}"
    }

    object Test {
        object Unit {
            const val junit = "junit:junit:${Versions.junit}"
            const val mockk = "io.mockk:mockk:${Versions.mockk}"
            const val kotlinTest = "org.jetbrains.kotlin:kotlin-test:${Versions.kotlin}"
            const val kotlinTestJunit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
            const val coreTesting = "androidx.arch.core:core-testing:${Versions.coreTesting}"
            const val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
        }

        object Android {
            const val junitExt = "androidx.test.ext:junit:${Versions.junitExt}"
            const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
            const val composeUiTest = "androidx.compose.ui:ui-test-junit4"
        }
    }
}

object Plugins {
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "org.jetbrains.kotlin.android"
    const val kotlinCompose = "org.jetbrains.kotlin.plugin.compose"
    const val kotlinKsp = "com.google.devtools.ksp" // KSP 추가
    const val hiltAndroid = "com.google.dagger.hilt.android"
}

object AppConfig {
    const val applicationId = "com.grensil.cleanarchitecturestudy"
    const val compileSdk = Versions.compileSdk
    const val minSdk = Versions.minSdk
    const val targetSdk = Versions.targetSdk
    const val versionCode = Versions.versionCode
    const val versionName = Versions.versionName
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    object BuildTypes {
        const val debug = "debug"
        const val release = "release"
    }
}

// 의존성 그룹을 쉽게 가져올 수 있도록 하는 확장
object DependencyGroups {
    val kotlinCore = listOf(
        Dependencies.Kotlin.stdlib,
        Dependencies.Kotlin.coroutinesCore,
        Dependencies.Kotlin.coroutinesAndroid
    )

    val androidxCore = listOf(
        Dependencies.AndroidX.coreKtx,
        Dependencies.AndroidX.appCompat,
        Dependencies.AndroidX.activityKtx,
        Dependencies.AndroidX.lifecycleViewmodel,
        Dependencies.AndroidX.lifecycleRuntime
    )

    val composeCore = listOf(
        Dependencies.Compose.ui,
        Dependencies.Compose.uiToolingPreview,
        Dependencies.Compose.material3,
        Dependencies.Compose.activity,
        Dependencies.Compose.lifecycle
    )

    val composeDebug = listOf(
        Dependencies.Compose.uiTooling,
        Dependencies.Compose.uiTestManifest
    )

    val hiltCore = listOf(
        Dependencies.Hilt.android,
        Dependencies.Hilt.navigationCompose
    )

    val networkCore = listOf(
        Dependencies.Network.retrofit,
        Dependencies.Network.retrofitGson,
        Dependencies.Network.okhttp,
        Dependencies.Network.okhttpLogging
    )

    val unitTest = listOf(
        Dependencies.Test.Unit.junit,
        Dependencies.Test.Unit.mockk,
        Dependencies.Test.Unit.coreTesting,
        Dependencies.Test.Unit.coroutineTest
    )

    val androidTest = listOf(
        Dependencies.Test.Android.junitExt,
        Dependencies.Test.Android.espresso
    )
}