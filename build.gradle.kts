// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    extra.apply {
        set("nav_version", "2.5.3")
        set("room_version", "2.5.1")
    }
}

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.googleAndroidLibrariesMapsplatformSecretsGradlePlugin) apply false
}