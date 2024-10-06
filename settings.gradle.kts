pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://api.mapbox.com/downloads/v2/releases/maven") }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://api.mapbox.com/downloads/v2/releases/maven") }
    }
}
rootProject.name = "restaurants"
include(":app")
