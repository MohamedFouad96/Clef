plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id ("kotlin-parcelize")
    id ("com.google.gms.google-services")
    id ("com.google.firebase.crashlytics")
    id ("de.mannodermaus.android-junit5")  version("1.8.2.1")
    id ("realm-android")
}

android {
    namespace = "com.cme.clef"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.cme.clef"
        minSdk = 25
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.cme.clef.InstrumentationTestRunner"
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

dependencies {

    // Support Dependencies for kotlin and UI
    implementation(platform(libs.androidx.compose.bom))
    supportDependencies.values.forEach { implementation(it) }

    // UI Dependencies
    uiDependencies.values.forEach { implementation(it) }

    // Firebase Dependencies
    firebaseDependencies.values.forEach { implementation(it) }


    // Dependencies for local unit tests
    supportDependenciesTest.values.forEach { testImplementation(it) }
    supportDependenciesTestRuntime.values.forEach { testRuntimeOnly(it) }

    // Dependencies for Android Test
    androidTestImplementation(platform(libs.androidx.compose.bom))
    supportDependenciesAndroidTest.values.forEach { androidTestImplementation(it) }
    supportDependenciesDebugAndroidTest.values.forEach { debugImplementation(it) }

    // Network Client
    networkClientDependencies.values.forEach { implementation(it) }

    // Dependency Injection
    dependencyInjectionDependencies.values.forEach { implementation(it) }



}