pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven (url = file("mc-artifacts"))
    }

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "adeum" -> useModule("com.appdynamics:appdynamics-gradle-plugin:23.4.2")
            }
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven (url = file("mc-artifacts"))
    }
}
rootProject.name = "AppDAgp8"
include (":app")