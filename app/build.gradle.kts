plugins {
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.pfm"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.pfm"
        minSdk = 29
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"


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
    implementation(libs.bundles.androidX)
    //Compose
    implementation(platform(libs.compose.bom))
    debugImplementation(libs.compose.tooling)
    implementation(libs.bundles.ui)
    implementation("androidx.compose.animation:animation:1.7.8")
    implementation("androidx.compose.foundation:foundation:1.7.8")
    implementation("androidx.compose.material:material:1.7.8")
    implementation("androidx.compose.material3:material3:1.3.1")
    implementation("androidx.compose.material3:material3-window-size-class:1.3.1")
    implementation("androidx.compose.material3:material3-adaptive-navigation-suite:1.4.0-alpha11")
    implementation("androidx.compose.material3.adaptive:adaptive:1.2.0-alpha02")
    implementation ("androidx.compose.material3.adaptive:adaptive-layout:1.2.0-alpha02")
    implementation ("androidx.compose.material3.adaptive:adaptive-navigation:1.2.0-alpha02")
    implementation("androidx.compose.runtime:runtime:1.7.8")
    implementation("androidx.compose.runtime:runtime-livedata:1.7.8")
    implementation("androidx.compose.runtime:runtime-rxjava2:1.7.8")
    implementation("androidx.compose.ui:ui:1.7.8")
    implementation("androidx.concurrent:concurrent-futures-ktx:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.1.0-beta01")
    implementation("androidx.contentpager:contentpager:1.0.0")
    implementation("androidx.coordinatorlayout:coordinatorlayout:1.2.0")
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.core:core-role:1.1.0")
    implementation("androidx.core:core-animation:1.0.0")
    androidTestImplementation("androidx.core:core-animation-testing:1.0.0")
    implementation("androidx.core:core-performance:1.0.0")
    implementation("androidx.core:core-google-shortcuts:1.1.0")
    implementation("androidx.core:core-remoteviews:1.1.0")
    implementation("androidx.core:core-splashscreen:1.2.0-beta01")
    implementation("androidx.core.uwb:uwb:1.0.0-alpha10")
    implementation("androidx.credentials:credentials:1.5.0")

}