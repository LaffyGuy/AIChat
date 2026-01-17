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

rootProject.name = "AIChat"
include(":app")
include(":core:essentials")
include(":core:common-android")
include(":features:init:domain")
include(":features:init:presentation")
include(":core:theme")
include(":navigation:host")
include(":navigation:common")
include(":features:main:domain")
include(":features:main:presentation")

include(":features:prompts:domain")
include(":features:prompts:presentation")
include(":data")
include(":glue")
include(":features:about:domain")
include(":features:about:presentation")
include(":core:data")
include(":features:chats:domain")
include(":features:chats:presentation")
