plugins {
    id(Config.Plugins.android)
    id(Config.Plugins.kotlinAndroid)
    id(Config.Plugins.kotlinKapt)
    id(Config.Plugins.kotlinParcelize)
    id(Config.Plugins.daggerHilt)
}

android {
    compileSdk = Config.Android.androidCompileSdkVersion
    buildToolsVersion = Config.Android.androidBuildToolsVersion

    defaultConfig {
        applicationId = Environments.Release.appId
        minSdk = Config.Android.androidMinSdkVersion
        targetSdk = Config.Android.androidTargetSdkVersion
        versionCode = Environments.Release.appVersionCode
        versionName = Environments.Release.appVersionName

        testInstrumentationRunner = Config.testRunner
    }

    buildTypes {
        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
            applicationIdSuffix = ".debug"
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
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(Dependencies.AndroidXDependencies.coreKtx)
    implementation(Dependencies.AndroidXDependencies.appCompat)
    implementation(Dependencies.AndroidXDependencies.activityCompose)
    implementation(Dependencies.AndroidXDependencies.lifecycleRuntimeKtx)
    implementation(Dependencies.AndroidXDependencies.composeUi)
    implementation(Dependencies.AndroidXDependencies.composeMaterial)
    implementation(Dependencies.AndroidXDependencies.composeTooling)
    implementation(Dependencies.AndroidXDependencies.composeToolingPreview)

    implementation(Dependencies.GoogleDependencies.materialDesign)
    implementation(Dependencies.GoogleDependencies.gson)
    implementation(Dependencies.GoogleDependencies.accompanistInsets)
    implementation(Dependencies.GoogleDependencies.accompanistSystemUiController)

    implementation(Dependencies.DaggerHiltDependencies.hiltCore)
    implementation(Dependencies.DaggerHiltDependencies.hiltNavigation)
    kapt(Dependencies.DaggerHiltDependencies.hiltCompiler)
}