plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
    
}

android {
    namespace = "com.danp.artexploreapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.danp.artexploreapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
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
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation ("com.google.code.gson:gson:2.10.1")


    // Authentication
    implementation(platform("com.google.firebase:firebase-bom:33.1.2"))
    implementation("com.google.firebase:firebase-auth")

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    //Navigation
    implementation (libs.androidx.navigation.compose)

    // Maps compose
    implementation("com.google.maps.android:maps-compose:2.11.5")

    // Maps SDK for Android
    implementation(libs.play.services.maps) //agregado google map
    implementation("com.google.android.gms:play-services-location:21.3.0") //agregado
    implementation(libs.android.maps.utils) //agregado google map utils
    implementation(libs.androidx.appcompat)
    implementation("com.google.accompanist:accompanist-permissions:0.19.0") // o la versión que desees

    //Coil
    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation ("com.squareup.picasso:picasso:2.8")

    //implementation 'com.journeyapps:zxing-android-embedded:4.3.0'
    implementation(libs.zxing.android.embedded)

    //hilt
    implementation("com.google.dagger:hilt-android:2.46")
    kapt("com.google.dagger:hilt-android-compiler:2.46")


}