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
    implementation(project(Modules.model))
    implementation(project(Modules.injector))
    implementation(project(Modules.coreCommon))
    implementation(project(Modules.corePresentation))

    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.paging)

    implementation(libs.google.material)

    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    implementation(libs.retrofit.core)
    implementation(libs.retrofit.gson)
}