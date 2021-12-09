object Config {
    object Android {
        const val androidMinSdkVersion = 21
        const val androidTargetSdkVersion = 31
        const val androidCompileSdkVersion = 31
        const val androidBuildToolsVersion = "30.0.3"
    }

    object ClassPaths {
        private const val gradlePlugin = "7.0.4"

        const val androidGradle = "com.android.tools.build:gradle:$gradlePlugin"
        const val kotlinGradle =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Dependencies.KotlinDependencies.kotlinVersion}"
        const val daggerHiltGradle =
            "com.google.dagger:hilt-android-gradle-plugin:${Dependencies.DaggerHiltDependencies.hiltCoreVersion}"
        const val mavenGoogle = "https://maven.google.com/"
        const val jitpack = "https://jitpack.io"
    }

    object Plugins {
        const val android = "android"
        const val androidApplication = "com.android.application"
        const val androidLibrary = "com.android.library"
        const val kotlinAndroid = "kotlin-android"
        const val kotlinKapt = "kotlin-kapt"
        const val kotlinParcelize = "kotlin-parcelize"
        const val daggerHilt = "dagger.hilt.android.plugin"
    }

    object Kotlin {
        const val jvmTargetVersion = "1.8"
    }

    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
}