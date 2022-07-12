import com.android.build.gradle.BaseExtension

/**
 * Basic android library plugin.
 * It uses for configuring android library modules.
 */
plugins {
    id("com.android.library")
    id("basic-android-convention")
    id("kotlin-android")
    id("basic-kotlin-convention")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

configure<BaseExtension> {
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isDebuggable = true
        }
    }
}