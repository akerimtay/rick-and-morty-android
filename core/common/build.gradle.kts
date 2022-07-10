plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
}

dependencies {
    implementation(projects.core.presentation)
    implementation(projects.core.strings)

    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    implementation(libs.retrofit.gson)
}