import dependencies.Dependencies

plugins {
    id(Config.Plugins.androidApplication)
    id(Config.Plugins.kotlinAndroid)
    id(Config.Plugins.kotlinKapt)
    id(Config.Plugins.kotlinParcelize)
    id(Config.Plugins.daggerHilt)
}

android {
    compileSdk = Config.Android.androidCompileSdkVersion

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
        jvmTarget = Config.Kotlin.jvmTargetVersion
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Config.Kotlin.kotlinCompilerExtensionVersion
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(Modules.resources))
    implementation(project(Modules.common))
    implementation(project(Modules.domain))
    implementation(project(Modules.data))
    implementation(project(Modules.navigation))
    implementation(project(Modules.characters))
    implementation(project(Modules.characterDetails))
    implementation(project(Modules.locations))
    implementation(project(Modules.episodes))
    implementation(project(Modules.settings))

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
}