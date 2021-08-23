object Dependencies {
    object KotlinDependencies {
        // https://kotlinlang.org/
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    }

    object AndroidXDependencies {
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"

        // https://developer.android.com/topic/libraries/architecture/lifecycle
        const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleKtx}"

        // https://developer.android.com/jetpack/compose/setup
        const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
        const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
        const val composeTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        const val composeToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    }

    object DaggerHiltDependencies {
        // https://dagger.dev/hilt/
        const val hiltCore = "com.google.dagger:hilt-android:${Versions.hiltCore}"
        const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hiltCore}"
        const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:${Versions.hiltCompose}"
    }

    object GoogleDependencies {
        // https://material.io/develop/android
        const val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"

        // https://github.com/google/gson
        const val gson = "com.google.code.gson:gson:${Versions.gson}"

        // https://github.com/google/accompanist
        const val accompanistInsets = "com.google.accompanist:accompanist-insets:${Versions.accompanist}"
        const val accompanistSystemUiController =
            "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}"
    }

    object CoroutinesDependencies {
        // https://github.com/Kotlin/kotlinx.coroutines
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    }

    object RetrofitDependencies {
        // https://github.com/square/retrofit
        const val core = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val coroutinesAdapter =
            "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofitCoroutinesAdapter}"
    }

    object OkHttpDependencies {
        // https://github.com/square/okhttp
        const val core = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    }

    object OtherDependencies {
        // https://github.com/JakeWharton/timber
        const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

        // http://facebook.github.io/shimmer-android/
        const val shimmer = "com.facebook.shimmer:shimmer:${Versions.shimmer}"
    }
}