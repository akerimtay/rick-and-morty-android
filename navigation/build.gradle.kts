import dependencies.Dependencies

plugins {
    id(Config.Plugins.androidLibrary)
    id(Config.Plugins.kotlinAndroid)
    id(Config.Plugins.kotlinKapt)
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
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.1"
    }
}

dependencies {
    implementation(project(Modules.resources))
    implementation(project(Modules.common))
    implementation(project(Modules.domain))

    implementation(Dependencies.AndroidXDependencies.coreKtx)
    implementation(Dependencies.AndroidXDependencies.appCompat)
    implementation(Dependencies.AndroidXDependencies.activityCompose)
    implementation(Dependencies.AndroidXDependencies.lifecycleRuntimeKtx)
    implementation(Dependencies.AndroidXDependencies.composeUi)
    implementation(Dependencies.AndroidXDependencies.composeMaterial)
    implementation(Dependencies.AndroidXDependencies.composeTooling)
    implementation(Dependencies.AndroidXDependencies.composeToolingPreview)
    implementation(Dependencies.AndroidXDependencies.composeLiveData)

    implementation(Dependencies.GoogleDependencies.accompanistInsets)
    implementation(Dependencies.GoogleDependencies.accompanistSystemUiController)
    implementation(Dependencies.GoogleDependencies.materialDesign)

    implementation(Dependencies.DaggerHiltDependencies.hiltCore)
    implementation(Dependencies.DaggerHiltDependencies.hiltNavigation)
    kapt(Dependencies.DaggerHiltDependencies.hiltCompiler)
}