pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "AndroidComposeTemplate"
include(":app")
include(":core")
include(":core:data")
include(":core:domain")
include(":core:ui")
include(":core:database")
include(":core:network")
include(":core:common")
include(":core:model")
include(":feature")
include(":feature:authentication")
include(":core:datastore")
include(":feature:main")
include(":feature:services")

