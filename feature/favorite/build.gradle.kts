plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    id(Plugins.kotlinKsp)
    id(Plugins.hiltAndroid)
}

android {
    namespace = "com.grensil.favorite"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0"
    }
    packagingOptions {
        exclude("META-INF/gradle/incremental.annotation.processors")
    }
}

dependencies {
    implementation(project(":core:domain"))

    // === Compose BOM (버전 통일) ===
    implementation(platform(Dependencies.Compose.bom))

    // === AndroidX Core ===
    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.AndroidX.lifecycleRuntime)
    implementation(Dependencies.AndroidX.lifecycleViewmodel)
    implementation(Dependencies.AndroidX.activityKtx)

    // === Compose UI ===
    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.uiGraphics)
    implementation(Dependencies.Compose.uiToolingPreview)
    implementation(Dependencies.Compose.material3)
    implementation(Dependencies.Compose.materialIconsExtended)
    implementation(Dependencies.Compose.activity)
    implementation(Dependencies.Compose.lifecycle)
    implementation(Dependencies.Compose.animation)

    // === Network ===
    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.retrofitGson)
    implementation(Dependencies.Network.okhttp)
    implementation(Dependencies.Network.okhttpLogging)

    // === Kotlin Coroutines ===
    implementation(Dependencies.Kotlin.coroutinesCore)
    implementation(Dependencies.Kotlin.coroutinesAndroid)

    // === Dependency Injection (Hilt) ===
    implementation(Dependencies.Hilt.android)
    implementation(Dependencies.Hilt.navigationCompose)
    ksp(Dependencies.Hilt.compiler)

    // === Image Loading ===
    implementation(Dependencies.Image.coilCompose)
    implementation(Dependencies.Image.coil)

    // === Android Testing ===
    androidTestImplementation(Dependencies.Test.Android.junitExt)
    androidTestImplementation(Dependencies.Test.Android.espresso)
    androidTestImplementation(platform(Dependencies.Compose.bom))
    androidTestImplementation(Dependencies.Test.Android.composeUiTest)
}