plugins {
    java
    kotlin("jvm") version "1.5.20"
    `maven-publish`
}

group = "net.projecttl"
version = "4.0.0"

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    repositories {
        maven("https://papermc.io/repo/repository/maven-public/")
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/") {
            name = "sonatype-oss-snapshots"
        }
    }

    dependencies {
        implementation("net.kyori:adventure-api:4.7.0")
        implementation(kotlin("stdlib"))
        compileOnly("com.destroystokyo.paper:paper-api:1.16.5-R0.1-SNAPSHOT")
    }
}