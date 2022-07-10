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