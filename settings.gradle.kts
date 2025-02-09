rootProject.name = "KotlinProject"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        maven {
            url = uri("https://company/com/maven2")
        }
        mavenLocal()
        gradlePluginPortal()
        jcenter()
    }
}

plugins {
    id("com.gradle.enterprise") version "3.16.2"
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        maven {
            url = uri("https://company/com/maven2")
        }
        mavenLocal()
        jcenter()
    }
}

include(":composeApp")