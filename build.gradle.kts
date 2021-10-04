plugins {
    java
    kotlin("jvm") version "1.5.31"
    id("org.jetbrains.dokka") version "1.5.0"
    `maven-publish`
}

group = "net.projecttl"
version = "4.1.5"

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.dokka")

    repositories {
        maven("https://papermc.io/repo/repository/maven-public/")
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/") {
            name = "sonatype-oss-snapshots"
        }
    }

    dependencies {
        implementation(kotlin("stdlib"))
        implementation("net.kyori:adventure-api:4.7.0")

        compileOnly("com.destroystokyo.paper:paper-api:1.16.5-R0.1-SNAPSHOT")
    }
}
