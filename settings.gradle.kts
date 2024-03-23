pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "TestXvanXml"
include(":app")
include(":core:ui")
include(":core:navigation")
include(":features:bottomMenu:host:presentation")
include(":features:bottomMenu:host:api")
include(":features:bottomMenu:host:tab_api")
