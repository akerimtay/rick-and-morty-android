plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(projects.moduleInjector)

    implementation(projects.core.common)
    implementation(projects.core.presentation)
    implementation(projects.core.strings)

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