group = "com.kamjin"
version = "1.0"

// define local const
val localAppName = "demo"
val localMainClassName = "com.kamjin.packdemo.MyAppKt"
val localJreDir = "D:\\Program Files\\Java\\jdk1.8.0_301\\jre"

plugins {
    id("org.gradle.java-library")
    kotlin("jvm") version "1.6.21"
    id("org.gradle.idea")
    application
}

//===============================package jar=====================================
sourceSets {
    main {
        java {
            srcDirs(
                "src/main/kotlin/"
            )
        }
    }
}

application.mainClass.set(localMainClassName)
java.sourceCompatibility = JavaVersion.VERSION_1_8

tasks {
    withType<JavaCompile>() {
        options.encoding = "UTF-8"
    }

    withType<Javadoc>() {
        options.encoding = "UTF-8"
    }

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }
}

tasks.jar {
    manifest {
        attributes("Main-Class" to localMainClassName)
    }

    // To avoid the duplicate handling strategy error
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    // To add all of the dependencies
    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }
    })
}
//===============================package jar=====================================

//===============================javaPackager=====================================
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("io.github.fvarrui:javapackager:1.7.0")
    }
}

apply {
    plugin("io.github.fvarrui.javapackager.plugin")
}

tasks.register<io.github.fvarrui.javapackager.gradle.PackageTask>("packageApp") {
    dependsOn(tasks.build)

    // mandatory
    mainClass = localMainClassName
    // optional
    isGenerateInstaller = false
    isAdministratorRequired = false
    platform = io.github.fvarrui.javapackager.model.Platform.auto

    isBundleJre = true
    jrePath = file(localJreDir)
    appName = localAppName
//    additionalResources = kotlin.collections.mutableListOf(file("extra.properties"))
//    linuxConfig {
//        ...
//    }
//    macConfig {
//        ...
//    }
}
//===============================javaPackager=====================================

dependencies {
    implementation(kotlin("stdlib"))
    implementation("no.tornado:tornadofx:1.7.20")
    implementation("ch.qos.logback:logback-classic:1.2.3")
}

description = localAppName
