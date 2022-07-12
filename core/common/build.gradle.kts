plugins {
    id("android-library-convention")
}

dependencies {
    implementation(projects.core.presentation)
    implementation(projects.core.strings)

    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    implementation(libs.retrofit.gson)
}