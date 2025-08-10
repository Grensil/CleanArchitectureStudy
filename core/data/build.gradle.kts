
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id(Plugins.kotlinKapt)
    id(Plugins.hiltAndroid)
}

android {
    namespace = "com.grensil.data"
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
    implementation(project(":core:domain"))

    // === AndroidX Core ===
    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.AndroidX.appCompat)

    // === Kotlin Coroutines ===
    implementation(Dependencies.Kotlin.coroutinesAndroid)

    // === Dependency Injection (Hilt) ===
    implementation(Dependencies.Hilt.android)
    kapt(Dependencies.Hilt.compiler)

    // === Network ===
    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.retrofitGson)
    implementation(Dependencies.Network.okhttp)
    implementation(Dependencies.Network.okhttpLogging)

    // === Room ===
    implementation(Dependencies.Room.runtime)
    implementation(Dependencies.Room.ktx)
    kapt(Dependencies.Room.compiler)

    // === Preferences ===
    implementation(Dependencies.AndroidX.preference)

    // === Material Design ===
    implementation(Dependencies.Material.material)

    // === Unit Testing ===
    testImplementation(Dependencies.Test.Unit.junit)
    testImplementation(Dependencies.Test.Unit.mockk)
    testImplementation(Dependencies.Test.Unit.kotlinTest)
    testImplementation(Dependencies.Test.Unit.kotlinTestJunit)
    testImplementation(Dependencies.Test.Unit.coreTesting)
    testImplementation(Dependencies.Test.Unit.coroutineTest)

    // === Android Testing ===
    androidTestImplementation(Dependencies.Test.Android.junitExt)
    androidTestImplementation(Dependencies.Test.Android.espresso)
}