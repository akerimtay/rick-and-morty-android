import dependencies.Dependencies

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
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = Config.Kotlin.jvmTargetVersion
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Config.Kotlin.kotlinCompilerExtensionVersion
    }
}

dependencies {
    implementation(Dependencies.AndroidXDependencies.coreKtx)
    implementation(Dependencies.AndroidXDependencies.appCompat)
    implementation(Dependencies.AndroidXDependencies.composeUi)
    implementation(Dependencies.AndroidXDependencies.composeMaterial)
    implementation(Dependencies.GoogleDependencies.accompanistSystemUiController)
    implementation(Dependencies.GoogleDependencies.materialDesign)
}