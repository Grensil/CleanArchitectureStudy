plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinCompose)
    id(Plugins.kotlinKapt)
    id(Plugins.hiltAndroid)
}

android {
    namespace = AppConfig.applicationId
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
        testInstrumentationRunner = AppConfig.testInstrumentationRunner
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
}

dependencies {
    // === 프로젝트 모듈 ===
    implementation(project(":feature:home"))
    implementation(project(":feature:favorite"))
    implementation(project(":core:domain"))
    implementation(project(":core:data"))

    // === Compose BOM (버전 통일) ===
    implementation(platform(Dependencies.Compose.bom))

    // === AndroidX Core ===
    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.AndroidX.lifecycleRuntime)
    implementation(Dependencies.AndroidX.lifecycleViewmodel)
    implementation(Dependencies.AndroidX.activityKtx)

    // === Kotlin Coroutines ===
    implementation(Dependencies.Kotlin.coroutinesCore)
    implementation(Dependencies.Kotlin.coroutinesAndroid)

    // === Compose UI ===
    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.uiGraphics)
    implementation(Dependencies.Compose.uiToolingPreview)
    implementation(Dependencies.Compose.material3)
    implementation(Dependencies.Compose.materialIconsExtended)
    implementation(Dependencies.Compose.activity)
    implementation(Dependencies.Compose.lifecycle)
    implementation(Dependencies.Compose.animation)

    // === Navigation ===
    implementation(Dependencies.Navigation.compose)
    implementation(Dependencies.Accompanist.navigationAnimation)

    // === Dependency Injection (Hilt) ===
    implementation(Dependencies.Hilt.android)
    implementation(Dependencies.Hilt.navigationCompose)
    kapt(Dependencies.Hilt.compiler)

    // === Network ===
    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.retrofitGson)
    implementation(Dependencies.Network.okhttp)
    implementation(Dependencies.Network.okhttpLogging)

    // === Image Loading ===
    implementation(Dependencies.Image.coilCompose)

    // === Material Design ===
    implementation(Dependencies.Material.material)

    // === Debug Dependencies ===
    debugImplementation(Dependencies.Compose.uiTooling)
    debugImplementation(Dependencies.Compose.uiTestManifest)

    // === Unit Testing ===
    testImplementation(Dependencies.Test.Unit.junit)

    // === Android Testing ===
    androidTestImplementation(Dependencies.Test.Android.junitExt)
    androidTestImplementation(Dependencies.Test.Android.espresso)
    androidTestImplementation(platform(Dependencies.Compose.bom))
    androidTestImplementation(Dependencies.Test.Android.composeUiTest)
}