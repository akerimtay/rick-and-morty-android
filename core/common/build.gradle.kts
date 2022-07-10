plugins {
    id(Config.Plugins.androidLibrary)
    id(Config.Plugins.kotlinAndroid)
    id(Config.Plugins.kotlinParcelize)
}

dependencies {
    implementation(projects.core.presentation)
    implementation(projects.core.strings)

    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    implementation(libs.retrofit.gson)
}