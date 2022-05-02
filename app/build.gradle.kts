plugins {
    id(Config.Plugins.androidApplication)
    id(Config.Plugins.kotlinAndroid)
    id(Config.Plugins.kotlinKapt)
    id(Config.Plugins.kotlinParcelize)
    id(Config.Plugins.navigationSafeArgs)
}

android {
    compileSdk = Config.Android.androidCompileSdkVersion

    defaultConfig {
        applicationId = Environment.Release.appId
        minSdk = Config.Android.androidMinSdkVersion
        targetSdk = Config.Android.androidTargetSdkVersion
        versionCode = Environment.Release.appVersionCode
        versionName = Environment.Release.appVersionName

        testInstrumentationRunner = Config.testRunner
    }

    buildTypes {
        getByName(Environment.BuildTypes.release) {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName(Environment.BuildTypes.debug) {
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
            applicationIdSuffix = ".debug"
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
    implementation(project(Modules.corePresentation))
    implementation(project(Modules.coreCommon))
    implementation(project(Modules.model))
    implementation(project(Modules.injector))
    implementation(project(Modules.featureCharacter))
    implementation(project(Modules.featureLocation))

    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.paging)

    implementation(libs.google.material)

    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    implementation(libs.retrofit.core)
    implementation(libs.retrofit.gson)

    implementation(libs.okhttp.core)
    implementation(libs.okhttp.logging.interceptor)

    implementation(libs.other.timber)
    releaseImplementation(libs.other.chucker.release)
    debugImplementation(libs.other.chucker.debug)
}