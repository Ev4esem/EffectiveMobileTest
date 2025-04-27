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

rootProject.name = "EffectiveMobileTest"
include(":app")
include(":data")
include(":domain")
include(":splash-impl")
include(":splash-api")
include(":resources")
include(":auth-api")
include(":auth-impl")
include(":main-impl")
include(":main-api")
include(":favourite-api")
include(":favourite-impl")
include(":presentation")

project(":auth-api").projectDir = File("features/auth/auth-api")
project(":auth-impl").projectDir = File("features/auth/auth-impl")
project(":splash-api").projectDir = File("features/splash/splash-api")
project(":splash-impl").projectDir = File("features/splash/splash-impl")
project(":resources").projectDir = File("libraries/resources")
project(":main-api").projectDir = File("features/main/main-api")
project(":main-impl").projectDir = File("features/main/main-impl")
project(":favourite-api").projectDir = File("features/favourite/favourite-api")
project(":favourite-impl").projectDir = File("features/favourite/favourite-impl")
project(":presentation").projectDir = File("libraries/presentation")
