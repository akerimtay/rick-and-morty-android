object Dependencies {
    object KotlinDependencies {
        const val kotlinVersion = "1.6.0"

        // https://kotlinlang.org/
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
    }

    object AndroidXDependencies {
        private const val coreKtxVersion = "1.6.0"
        private const val appCompatVersion = "1.3.1"
        private const val lifecycleRuntimeKtxVersion = "2.3.1"
        private const val lifecycleViewModelKtxVersion = "2.2.0"
        private const val roomVersion = "2.3.0"
        private const val roomPagingVersion = "2.4.0-alpha04"

        const val coreKtx = "androidx.core:core-ktx:$coreKtxVersion"
        const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"

        // https://developer.android.com/topic/libraries/architecture/lifecycle
        const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleRuntimeKtxVersion"
        const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleViewModelKtxVersion"

        // https://developer.android.com/training/data-storage/room#kts
        const val roomRuntime = "androidx.room:room-runtime:$roomVersion"
        const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
        const val roomKtx = "androidx.room:room-ktx:$roomVersion"
        const val roomPaging = "androidx.room:room-paging:$roomPagingVersion"
    }

    object DaggerHiltDependencies {
        const val hiltCoreVersion = "2.38.1"

        // https://dagger.dev/hilt/
        const val hiltCore = "com.google.dagger:hilt-android:$hiltCoreVersion"
        const val hiltCompiler = "com.google.dagger:hilt-compiler:$hiltCoreVersion"
    }

    object GoogleDependencies {
        private const val materialDesignVersion = "1.4.0"
        private const val gsonVersion = "2.8.8"

        // https://material.io/develop/android
        const val materialDesign = "com.google.android.material:material:$materialDesignVersion"

        // https://github.com/google/gson
        const val gson = "com.google.code.gson:gson:$gsonVersion"
    }

    object CoroutinesDependencies {
        private const val coroutinesVersion = "1.4.3"

        // https://github.com/Kotlin/kotlinx.coroutines
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
    }

    object RetrofitDependencies {
        private const val retrofitVersion = "2.9.0"

        // https://github.com/square/retrofit
        const val core = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val gson = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
    }

    object OkHttpDependencies {
        private const val okHttpVersion = "4.9.1"

        // https://github.com/square/okhttp
        const val core = "com.squareup.okhttp3:okhttp:$okHttpVersion"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$okHttpVersion"
    }

    object OtherDependencies {
        private const val timberVersion = "4.7.1"
        private const val shimmerVersion = "0.5.0"

        // https://github.com/JakeWharton/timber
        const val timber = "com.jakewharton.timber:timber:$timberVersion"

        // http://facebook.github.io/shimmer-android/
        const val shimmer = "com.facebook.shimmer:shimmer:$shimmerVersion"
    }
}