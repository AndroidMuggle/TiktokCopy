plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.android.hilt)
    alias(libs.plugins.android.ksp)
    alias(libs.plugins.kotlinx.serialzation)
}

android {
    namespace = "com.muggle.tiktokcopy"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.muggle.tiktokcopy"
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
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
//    kotlinOptions {
//        jvmTarget = "11"
//    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
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
    implementation(libs.androidx.hilt)
    implementation(libs.androidx.hilt.nav)
    ksp(libs.androidx.hilt.compiler)
    implementation(libs.compose.coil)
    implementation(libs.compose.coil.network)
    implementation(libs.androidx.ktor.cio)
    implementation(libs.androidx.ktor.core)
    implementation(libs.androidx.ktor.client)
    implementation(libs.androidx.ktor.gson)
//    implementation(libs.androidx.ktor.json)
    implementation(libs.androidx.ktor.negotiation)
//    implementation(libs.androidx.viewmodel.ktx)
    implementation(libs.androidx.media.compose)
    implementation(libs.androidx.media.exoplayer)
    implementation(libs.androidx.media.ui)
    implementation(libs.androidx.media.common)
    implementation(libs.androidx.navigation.compose)
}