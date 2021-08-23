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

tasks.register("type", Delete::class.java) {
    delete(rootProject.buildDir)
}