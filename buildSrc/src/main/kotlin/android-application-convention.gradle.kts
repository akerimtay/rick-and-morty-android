import com.android.build.gradle.BaseExtension

/**
 * Basic android application plugin.
 * It uses for configuring application module.
 */
plugins {
    id("com.android.application")
    id("basic-android-convention")
    id("kotlin-android")
    id("basic-kotlin-convention")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
}

configure<BaseExtension> {
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isDebuggable = true
            applicationIdSuffix = ".debug"
        }
    }
}