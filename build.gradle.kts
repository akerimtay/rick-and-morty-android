import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath(Config.ClassPaths.buildGradle)
        classpath(Config.ClassPaths.kotlin)
        classpath(Config.ClassPaths.navigationSafeArgs)
    }
}

allprojects {
    repositories {
        mavenCentral()
        maven(url = Config.ClassPaths.mavenGoogle)
        maven(url = Config.ClassPaths.jitpack)
    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions.freeCompilerArgs += listOf(
            "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
        )
    }
}

subprojects {
    project.plugins.applyBaseConfig(project)
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}

fun BaseExtension.baseConfig() {
    compileSdkVersion(Config.Android.androidCompileSdkVersion)

    defaultConfig {
        minSdk = Config.Android.androidMinSdkVersion
        targetSdk = Config.Android.androidTargetSdkVersion

        testInstrumentationRunner = Config.testRunner
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName(Environment.BuildTypes.release) {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName(Environment.BuildTypes.debug) {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = Config.Kotlin.jvmTargetVersion
        }
    }
}

fun PluginContainer.applyBaseConfig(project: Project) {
    whenPluginAdded {
        when (this) {
            is AppPlugin -> {
                project.extensions
                    .getByType<AppExtension>()
                    .apply {
                        baseConfig()
                    }
            }
            is LibraryPlugin -> {
                project.extensions
                    .getByType<LibraryExtension>()
                    .apply {
                        baseConfig()
                    }
            }
        }
    }
}