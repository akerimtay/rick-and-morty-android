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
