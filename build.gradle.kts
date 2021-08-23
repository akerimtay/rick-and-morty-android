buildscript {
    val gradlePluginVersion = "7.0.1"
    val kotlinVersion = "1.5.21"
    val hiltCoreVersion = "2.38.1"

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:$gradlePluginVersion")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.google.dagger:hilt-android-gradle-plugin:$hiltCoreVersion")
    }
}

tasks.register("type", Delete::class.java) {
    delete(rootProject.buildDir)
}