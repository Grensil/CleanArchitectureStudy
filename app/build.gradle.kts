plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    kotlin("kapt") // 추가
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.grensil.cleanarchitecturestudy"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.grensil.cleanarchitecturestudy"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {
    implementation(project(":feature:home"))
    implementation(project(":core:domain"))
    implementation(project(":core:data"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(AndroidX.CORE_KTX)
    implementation(AndroidX.APP_COMPAT)
    implementation(AndroidX.LIFECYCLE_RUNTIME_KTX)
    implementation(AndroidX.LIFECYCLE_VIEWMODEL_KTX)

    //Activity extension, referring with  "by viewModels"
    implementation(AndroidX.ACTIVITY_KTX)

    //retrofit + gson
    implementation(Libraries.RETROFIT)
    implementation(Libraries.RETROFIT_CONVERTER_GSON)

    //okhttp
    implementation(Libraries.OKHTTP)
    implementation(Libraries.OKHTTP_LOGGING_INTERCEPTOR)

    //Coroutines
    implementation(Kotlin.COROUTINES_ANDROID)

    //Hilt
    implementation(Google.HILT_ANDROID)
    kapt(Google.HILT_ANDROID_COMPILER)

    //compose coil
    implementation(Libraries.COIL)

    //hilt
    implementation(Google.HILT_COMPOSE)
    implementation(Google.NAVIGATION_COMPOSE)

    //compose
    implementation(platform("androidx.compose:compose-bom:2023.01.00"))

    implementation(Compose.COMPOSE_UI)
    implementation(Compose.COMPOSE_ANIMATION)
    implementation(Compose.COMPOSE_MATERIAL)
    implementation(Compose.COMPOSE_MATERIAL_3)
    implementation(Compose.COMPOSE_MATERIAL_ICON_EXTENDED)
    implementation(Compose.COMPOSE_LIFECYCLE_RUNTIME)
    implementation(Compose.COMPOSE_UI_TOOLING_PREVIEW)
    debugImplementation(Compose.COMPOSE_UI_TOOLING_DEBUG)

    implementation(Compose.COMPOSE_LIFECYCLE_RUNTIME)
    implementation(Compose.COMPOSE_ACTIVITY)

    implementation(Accompanist.NAVIGATION_ANIMATION)
}


object Versions {
    //kotlin
    const val KOTLIN_VERSION = "1.8.0"
    const val KOTLINX_COROUTINES = "1.5.2"

    //build
    const val COMPILE_SDK_VERSION = 35
    const val MIN_SDK_VERSION = 23
    const val TARGET_SDK_VERSION = 35
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0.0"

    //androidx
    const val CORE_KTX = "1.8.0"
    const val APP_COMPAT = "1.4.0"
    const val ACTIVITY_KTX = "1.4.0"
    const val LIFECYCLE_KTX = "2.4.0"
    const val PREFERENCE = "1.1.1"
    const val HILT_VIEWMODEL = "2.28-alpha"
    const val KAPT_HILT = "1.0.0-alpha02"

    //google
    const val HILT = "2.48"
    const val DATASTORE = "1.0.0"

    //libraries
    const val RETROFIT = "2.9.0"
    const val OKHTTP = "4.9.0"
    const val OKHTTP_LOGGING_INTERCEPTOR = "4.7.2"
    const val COIL = "1.4.0"
    const val COIL_COMPOSE = "2.3.0"

    //test
    const val JUNIT = "4.13.2"
    const val MOCKK = "1.10.0"
    const val KOTLIN_TEST = "1.8.0"
    const val KOTLIN_TEST_JUNIT = "1.8.0"
    const val CORE_TESTING = "2.1.0"
    const val ANDROID_JUNIT_EXT = "1.1.5"
    const val ANDROID_JUNIT = "1.1.2"
    const val ESPRESSO_CORE = "3.3.0"
}

object Kotlin {
    const val KOTLIN_STDLIB      = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.KOTLIN_VERSION}"
    const val COROUTINES_CORE    = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.KOTLINX_COROUTINES}"
    const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.KOTLINX_COROUTINES}"
}

object AndroidX {
    const val CORE_KTX                = "androidx.core:core-ktx:${Versions.CORE_KTX}"
    const val APP_COMPAT              = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"

    const val ACTIVITY_KTX            = "androidx.activity:activity-ktx:${Versions.ACTIVITY_KTX}"

    const val LIFECYCLE_VIEWMODEL_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE_KTX}"
    const val LIFECYCLE_RUNTIME_KTX  = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE_KTX}"

    const val PREFERENCE = "androidx.preference:preference-ktx:${Versions.PREFERENCE}"

    //For Hilt support for ViewModel
    const val HILT_VIEWMODEL = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.HILT_VIEWMODEL}"
    const val KAPT_HILT = "androidx.hilt:hilt-compiler:${Versions.KAPT_HILT}"
}

object Google {
    const val HILT_ANDROID          = "com.google.dagger:hilt-android:${Versions.HILT}"
    const val HILT_ANDROID_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.HILT}"

    const val HILT_COMPOSE = "androidx.hilt:hilt-navigation-compose:1.0.0"
    const val NAVIGATION_COMPOSE = "androidx.navigation:navigation-compose:2.5.3"

}

object Libraries {
    const val RETROFIT                   = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val RETROFIT_CONVERTER_GSON    = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}"

    const val OKHTTP                     = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
    const val OKHTTP_LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP_LOGGING_INTERCEPTOR}"
    const val OKHTTP_URLCONNECTION = "com.squareup.okhttp3:okhttp-urlconnection:${Versions.OKHTTP}"

    const val COIL = "io.coil-kt:coil:${Versions.COIL}"
    const val COIL_COMPOSE = "io.coil-kt:coil-compose:${Versions.COIL_COMPOSE}"
    const val COIL_SVG = "io.coil-kt:coil-svg:${Versions.COIL}"
}

object UnitTest {
    const val JUNIT = "junit:junit:${Versions.JUNIT}"
    const val MOCKK = "io.mockk:mockk:${Versions.MOCKK}"
    const val KOTLIN_TEST = "org.jetbrains.kotlin:kotlin-test:${Versions.KOTLIN_TEST}"
    const val KOTLIN_TEST_JUNIT = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.KOTLIN_TEST_JUNIT}"
    const val CORE_TESTING = "androidx.arch.core:core-testing:${Versions.CORE_TESTING}"
    const val ANDROID_JUNIT_EXT = "androidx.test.ext:junit:${Versions.ANDROID_JUNIT_EXT}"
    const val COROUTINE_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.KOTLINX_COROUTINES}"
}

object AndroidTest {
    const val ANDROID_JUNIT = "androidx.test.ext:junit:${Versions.ANDROID_JUNIT}"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
}

object Compose {
    const val COMPOSE_UI = "androidx.compose.ui:ui"
    const val COMPOSE_ANIMATION = "androidx.compose.animation:animation"
    const val COMPOSE_MATERIAL = "androidx.compose.material:material"
    const val COMPOSE_MATERIAL_3 = "androidx.compose.material3:material3"
    const val COMPOSE_MATERIAL_ICON_EXTENDED = "androidx.compose.material:material-icons-extended"
    const val COMPOSE_UI_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview"
    const val COMPOSE_UI_TOOLING_DEBUG = "androidx.compose.ui:ui-tooling"

    const val COMPOSE_LIFECYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime-compose:2.6.0-alpha01"
    const val COMPOSE_ACTIVITY = "androidx.activity:activity-compose:1.5.1"
}

object Accompanist {
    const val NAVIGATION_ANIMATION = "com.google.accompanist:accompanist-navigation-animation:0.31.1-alpha"
}