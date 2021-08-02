plugins {
    java
    id("com.github.johnrengelman.shadow") version "7.0.0"
    `maven-publish`
}

group = properties["apiGroup"]!!
version = properties["apiVersion"]!!

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.17.1-R0.1-SNAPSHOT")
}

tasks {
    withType<JavaCompile> {
        options.encoding = "UTF-8"

        sourceCompatibility = "11"
        targetCompatibility = "11"
    }

    processResources {
        filesMatching("*.yml") {
            expand(project.properties)
        }
    }

    create<Jar>("sourceJar") {
        archiveClassifier.set("source")
        from(sourceSets["main"].allSource)
    }

    shadowJar {
        archiveClassifier.set(project.version.toString())
        archiveBaseName.set(project.name)
        archiveVersion.set("")
    }
}

publishing {
    publications {
        create<MavenPublication>(project.name) {
            artifact(tasks["sourceJar"])
            from(components["java"])
        }
    }
}
