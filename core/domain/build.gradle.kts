plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id(Plugins.kotlinKsp)
    id(Plugins.hiltAndroid)
}

android {
    namespace = "com.grensil.domain"
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
        jvmTarget = "11"
    }
}

dependencies {
    // === Kotlin ===
    implementation(Dependencies.Kotlin.stdlib)

    // === Coroutines (순수 코틀린) ===
    implementation(Dependencies.Kotlin.coroutinesCore)

    // === Dependency Injection (Hilt) ===
    implementation(Dependencies.Hilt.android)
    ksp(Dependencies.Hilt.compiler)
}