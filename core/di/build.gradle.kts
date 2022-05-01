plugins {
    id(Config.Plugins.androidLibrary)
    id(Config.Plugins.kotlinAndroid)
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.dagger)
}