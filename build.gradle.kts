plugins {
    java
    kotlin("jvm") version "1.5.30"
    id("org.jetbrains.dokka") version "1.5.0"
    `maven-publish`
}

group = "net.projecttl"
version = "4.1.3"

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin= "org.jetbrains.dokka")

    repositories {
        maven("https://papermc.io/repo/repository/maven-public/")
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/") {
            name = "sonatype-oss-snapshots"
        }
    }

    dependencies {
        implementation("net.kyori:adventure-api:4.7.0")
        implementation(kotlin("stdlib"))
        compileOnly("io.papermc.paper:paper-api:1.17.1-R0.1-SNAPSHOT")
    }
}
