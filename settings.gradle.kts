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

// Core
include(":core:presentation")
include(":core:common")
include(":core:di")

// DI
include(":moduleInjector")
