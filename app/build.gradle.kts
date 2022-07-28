plugins {
    id("android-application-convention")
}

android {
    defaultConfig {
        applicationId = "com.akerimtay.rickandmorty"
    }
}

dependencies {
    implementation(projects.moduleInjector)

    implementation(projects.core.common)
    implementation(projects.core.presentation)
    implementation(projects.core.strings)
    implementation(projects.core.utils)

    implementation(projects.feature.character)
    implementation(projects.feature.location)
    implementation(projects.feature.episode)
    implementation(projects.feature.settings)

    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.paging)

    implementation(libs.google.material)

    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    implementation(libs.retrofit.core)
    implementation(libs.retrofit.gson)

    implementation(libs.okhttp.core)
    implementation(libs.okhttp.logging.interceptor)

    implementation(libs.other.timber)
    releaseImplementation(libs.other.chucker.release)
    debugImplementation(libs.other.chucker.debug)
}