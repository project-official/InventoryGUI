plugins {
    kotlin("jvm") version "1.5.20"
    java
}

var pluginGroup = "net.projecttl"
var pluginVersion = "4.0.0"

subprojects {
    apply {
        plugin("org.jetbrains.kotlin.jvm")
    }

    group = pluginGroup
    version = pluginVersion

    repositories {
        mavenCentral()
        maven("https://papermc.io/repo/repository/maven-public/")
    }

    val implementation by configurations

    dependencies {
        implementation(kotlin("stdlib"))
        compileOnly("io.papermc.paper:paper-api:1.17-R0.1-SNAPSHOT")
    }
}

group = pluginGroup
version = pluginVersion

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    implementation(kotlin("stdlib"))
    compileOnly("io.papermc.paper:paper-api:1.17-R0.1-SNAPSHOT")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}