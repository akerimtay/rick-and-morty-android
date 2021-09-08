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
    implementation(project(Modules.resources))
    implementation(project(Modules.common))
    implementation(project(Modules.navigation))

    implementation(Dependencies.AndroidXDependencies.coreKtx)
    implementation(Dependencies.AndroidXDependencies.appCompat)
    implementation(Dependencies.AndroidXDependencies.activityCompose)
    implementation(Dependencies.AndroidXDependencies.lifecycleRuntimeKtx)
    implementation(Dependencies.AndroidXDependencies.composeUi)
    implementation(Dependencies.AndroidXDependencies.composeMaterial)
    implementation(Dependencies.AndroidXDependencies.composeTooling)
    implementation(Dependencies.AndroidXDependencies.composeToolingPreview)
    implementation(Dependencies.AndroidXDependencies.composeLiveData)

    implementation(Dependencies.GoogleDependencies.materialDesign)
    implementation(Dependencies.GoogleDependencies.accompanistInsets)
    implementation(Dependencies.GoogleDependencies.accompanistSystemUiController)

    implementation(Dependencies.DaggerHiltDependencies.hiltCore)
    implementation(Dependencies.DaggerHiltDependencies.hiltNavigation)
    kapt(Dependencies.DaggerHiltDependencies.hiltCompiler)

    implementation(Dependencies.OtherDependencies.timber)
}