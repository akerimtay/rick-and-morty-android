plugins {
    id(Config.Plugins.androidLibrary)
    id(Config.Plugins.kotlinAndroid)
}

android {
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.paging)

    implementation(libs.google.material)
}