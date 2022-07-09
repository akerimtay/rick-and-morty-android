plugins {
    id(Config.Plugins.androidLibrary)
    id(Config.Plugins.kotlinAndroid)
    id(Config.Plugins.kotlinParcelize)
}

dependencies {
    implementation(project(Modules.coreStrings))
    implementation(project(Modules.corePresentation))

    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    implementation(libs.retrofit.gson)
}