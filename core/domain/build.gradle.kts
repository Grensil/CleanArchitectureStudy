plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id(Plugins.kotlinKsp)
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

    implementation(Dependencies.AndroidX.coreKtx)

    //kotlin
    implementation(Dependencies.Kotlin.stdlib)

    //Coroutines
    implementation(Dependencies.Kotlin.coroutinesAndroid)

    //retrofit
    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.retrofitGson)

    //okHttp
    implementation(Dependencies.Network.okhttp)
    implementation(Dependencies.Network.okhttpLogging)
    implementation(Dependencies.Network.okhttpUrlConnection)

    //hilt
    implementation(Dependencies.Hilt.android)
    implementation(Dependencies.Hilt.navigationCompose)
    ksp(Dependencies.Hilt.compiler)
}