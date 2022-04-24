enableFeaturePreview("VERSION_CATALOGS")

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("config/dependencies.toml"))
        }
    }
}

rootProject.name = "RickAndMorty"
include(":app")

// Shared
include(":resources")
include(":core:presentation")
include(":core:common")