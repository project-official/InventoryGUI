plugins {
    java
    kotlin("jvm") version "1.5.31"
}

group = "net.projecttl"
version = "4.1.5-SNAPSHOT"

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    repositories {
        mavenCentral()
        maven("https://jitpack.io/")
        maven("https://papermc.io/repo/repository/maven-public/")
        maven("https://repo.projecttl.net/repository/maven-public")
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
    }

    dependencies {
        implementation("net.kyori:adventure-api:4.7.0")
        implementation(kotlin("stdlib"))

        compileOnly("io.papermc.paper:paper-api:1.17.1-R0.1-SNAPSHOT")
    }
}
