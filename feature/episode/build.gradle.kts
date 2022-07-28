plugins {
    id("android-library-convention")
}

dependencies {
    implementation(projects.moduleInjector)

    implementation(projects.core.common)
    implementation(projects.core.presentation)
    implementation(projects.core.strings)
    implementation(projects.core.utils)

    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.swiperefreshlayout)
    implementation(libs.androidx.paging)

    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    implementation(libs.retrofit.core)
    implementation(libs.retrofit.gson)

    implementation(libs.glide.core)

    implementation(libs.google.material)
    implementation(libs.other.timber)
}