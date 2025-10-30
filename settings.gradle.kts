pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        maven ("https://maven.aliyun.com/repository/google")
        maven("https://maven.aliyun.com/repository/public") // 阿里云镜像
        maven("https://maven.aliyun.com/repository/central") // Central 仓库镜像
        maven("https://maven.aliyun.com/repository/gradle-plugin") // Gradle 插件镜像

        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven ("https://maven.aliyun.com/repository/google")
        maven("https://maven.aliyun.com/repository/public") // 阿里云镜像
        maven("https://maven.aliyun.com/repository/central") // Central 仓库镜像
        maven("https://maven.aliyun.com/repository/gradle-plugin") // Gradle 插件镜像
        google()
        mavenCentral()
    }
}

rootProject.name = "TiktokCopy"
include(":app")
