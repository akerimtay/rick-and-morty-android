plugins {
    id(Config.Plugins.androidLibrary)
    id(Config.Plugins.kotlinAndroid)
}

android {
    compileSdk = Config.Android.androidCompileSdkVersion

    defaultConfig {
        minSdk = Config.Android.androidMinSdkVersion
        targetSdk = Config.Android.androidTargetSdkVersion

        testInstrumentationRunner = Config.testRunner
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName(Environment.BuildTypes.release) {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName(Environment.BuildTypes.debug) {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = Config.Kotlin.jvmTargetVersion
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(Modules.resources))

    implementation(libs.androidx.lifecycle.compiler)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.paging)

    implementation(libs.google.material)
    implementation(libs.google.gson)

    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)
}