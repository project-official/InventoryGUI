plugins {
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = rootProject.group
version = rootProject.version

repositories {
    maven { url = uri("https://papermc.io/repo/repository/maven-public/") }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.17.1-R0.1-SNAPSHOT")
    implementation(project(":InventoryGUI-api"))
}