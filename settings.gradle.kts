pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenLocal()

        maven {
            url = uri("https://repo1.maven.org/maven2/")
        }

        maven {
            url = uri("http://maven.aliyun.com/nexus/content/groups/public")

            isAllowInsecureProtocol = true
        }

        maven {
            url = uri("https://repo.maven.apache.org/maven2/")
        }
    }
}
rootProject.name = "javafx-gradle-package2exe-demo"
