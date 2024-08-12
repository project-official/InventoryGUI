plugins {
    java
    kotlin("jvm") version "2.0.0"
    id("com.gradleup.shadow") version "8.3.0"
    id("org.jetbrains.dokka") version "1.9.20"

    `maven-publish`
}

group = "net.projecttl"
version = "4.6.0"

allprojects {
    apply(plugin = "com.gradleup.shadow")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.dokka")

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    }

    repositories {
        mavenCentral()
    }
}

subprojects {
    group = rootProject.group
    version = rootProject.version

    repositories {
        maven("https://papermc.io/repo/repository/maven-public/")
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/") {
            name = "sonatype-oss-snapshots"
        }
    }

    dependencies {
        implementation(kotlin("stdlib"))
        compileOnly("io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")
        if (this@subprojects.name != "InventoryGUI-api") {
            dependencies {
                implementation(project(":InventoryGUI-api"))
            }
        }
    }
}
