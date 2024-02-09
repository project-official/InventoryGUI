plugins {
    java
    kotlin("jvm") version "1.9.21"
    id("org.jetbrains.dokka") version "1.9.10"
    id("com.github.johnrengelman.shadow") version "8.1.1"

    `maven-publish`
}

group = "net.projecttl"
version = "4.5.1"

allprojects {
    apply(plugin = "com.github.johnrengelman.shadow")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.dokka")

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
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
        compileOnly("io.papermc.paper:paper-api:1.20.3-R0.1-SNAPSHOT")
        if (this@subprojects.name != "InventoryGUI-api") {
            dependencies {
                implementation(project(":InventoryGUI-api"))
            }
        }
    }
}
