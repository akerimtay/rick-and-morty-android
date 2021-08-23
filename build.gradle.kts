buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Config.ClassPaths.androidGradle)
        classpath(Config.ClassPaths.kotlinGradle)
        classpath(Config.ClassPaths.daggerHiltGradle)
    }
}

allprojects {
    repositories {
        mavenCentral()
        maven(url = Config.ClassPaths.mavenGoogle)
        maven(url = Config.ClassPaths.jitpack)
    }
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}