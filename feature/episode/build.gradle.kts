plugins {
    id(Config.Plugins.androidLibrary)
    id(Config.Plugins.kotlinAndroid)
    id(Config.Plugins.kotlinKapt)
}

android {
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(Modules.injector))
    implementation(project(Modules.coreCommon))
    implementation(project(Modules.corePresentation))
    implementation(project(Modules.coreStrings))

    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.paging)

    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    implementation(libs.google.material)
    implementation(libs.other.timber)
}