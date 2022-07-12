plugins {
    id("android-library-convention")
}

dependencies {
    implementation(projects.core.strings)

    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.paging)

    implementation(libs.javax.inject)

    implementation(libs.google.material)
}