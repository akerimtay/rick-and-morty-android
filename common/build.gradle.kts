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
}

dependencies {
    implementation(project(Modules.resources))

    implementation(Dependencies.AndroidXDependencies.lifecycleViewModelKtx)

    implementation(Dependencies.GoogleDependencies.materialDesign)

    implementation(Dependencies.RetrofitDependencies.core)
    implementation(Dependencies.RetrofitDependencies.gson)
    
    implementation(Dependencies.CoroutinesDependencies.coroutines)
    implementation(Dependencies.CoroutinesDependencies.coroutinesAndroid)
}