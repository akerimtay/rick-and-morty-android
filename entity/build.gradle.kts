plugins {
    id(Config.Plugins.androidLibrary)
    id(Config.Plugins.kotlinAndroid)
    id(Config.Plugins.kotlinParcelize)
}

dependencies {
    implementation(project(Modules.corePresentation))
    implementation(project(Modules.coreStrings))
}