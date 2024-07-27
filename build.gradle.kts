// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.crashlytics)  apply false
    alias(libs.plugins.google.services)  apply false

}


buildscript {
    dependencies {
        classpath(libs.realm.plugin)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://repo.itextsupport.com/android")
    }
}