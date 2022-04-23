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
include(":common")
include(":resources")

// Core
include(":core:presentation")
include(":core:common")