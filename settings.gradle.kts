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
include(":model")

// Core
include(":core:presentation")
include(":core:common")

// DI
include(":moduleInjector")

// Features
include(":features:character")
include(":features:location")
