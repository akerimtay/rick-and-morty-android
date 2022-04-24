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

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions.freeCompilerArgs += listOf(
            "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
        )
    }
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}