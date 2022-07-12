plugins {
    `version-catalog`
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

// VersionCatalog hasn't accessible yet for precompiled scripts
// https://github.com/gradle/gradle/issues/15383#issuecomment-779893192
dependencies {
    implementation("com.android.tools.build:gradle:7.1.3")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.0")
}