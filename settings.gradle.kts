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
include(":entity")

// Core
include(":core:presentation")
include(":core:common")
include(":core:strings")

// DI
include(":moduleInjector")

// Features
include(":feature:character")
include(":feature:location")
include(":feature:episode")
include(":feature:settings")
