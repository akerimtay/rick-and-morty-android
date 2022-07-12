@file:Suppress("UnstableApiUsage")

enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "RickAndMorty"

include(":app")

// DI
include(":module-injector")

// Core
include(":core:presentation")
include(":core:common")
include(":core:strings")

// Features
include(":feature:character")
include(":feature:location")
include(":feature:episode")
include(":feature:settings")

/**
 * All libs' versions has been declared in the project's file (/gradle/libs.versions.toml)
 * [Gradle version catalog feature](https://docs.gradle.org/current/userguide/platforms.html)
 * @since Gradle 7.4
 */
dependencyResolutionManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven(url = "https://maven.google.com/")
        maven(url = "https://jitpack.io")
    }
}
pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven(url = "https://maven.google.com/")
        maven(url = "https://jitpack.io")
    }
}