enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("config/dependencies.toml"))
        }
    }
}

include(":app")

// DI
include(":moduleInjector")

// Core
include(":core:presentation")
include(":core:common")
include(":core:strings")

// Features
include(":feature:character")
include(":feature:location")
include(":feature:episode")
include(":feature:settings")
