plugins {
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = rootProject.group
version = rootProject.version

dependencies {
    implementation(project(":InventoryGUI-api"))
}