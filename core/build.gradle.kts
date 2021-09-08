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
}

dependencies {
    implementation(project(Modules.common))

    implementation(project(Modules.characterData))
    implementation(project(Modules.characterDomain))

    implementation(Dependencies.RetrofitDependencies.core)
    implementation(Dependencies.RetrofitDependencies.gson)
    implementation(Dependencies.OkHttpDependencies.core)
    implementation(Dependencies.OkHttpDependencies.loggingInterceptor)

    implementation(Dependencies.AndroidXDependencies.roomKtx)
    implementation(Dependencies.AndroidXDependencies.roomRuntime)
    implementation(Dependencies.AndroidXDependencies.roomPaging)
    kapt(Dependencies.AndroidXDependencies.roomCompiler)

    implementation(Dependencies.DaggerHiltDependencies.hiltCore)
    kapt(Dependencies.DaggerHiltDependencies.hiltCompiler)
}