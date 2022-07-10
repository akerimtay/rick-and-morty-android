enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

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

pluginManagement {
    repositories {
        google()
        mavenCentral()
//        maven("https://plugins.gradle.org/m2/")//for kapt plugin 1.5.30
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}