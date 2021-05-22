plugins {
    java
    id("com.github.johnrengelman.shadow") version "7.0.0"
    `maven-publish`
}

group = properties["apiGroup"]!!
version = properties["apiVersion"]!!

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
    maven(url = "https://oss.sonatype.org/content/repositories/snapshots/") {
        name = "sonatype-oss-snapshots"
    }
}

dependencies {
    implementation("net.kyori:adventure-api:4.7.0")
    compileOnly("com.destroystokyo.paper:paper-api:1.16.5-R0.1-SNAPSHOT")
}

val shade = configurations.create("shade")
shade.extendsFrom(configurations.implementation.get())

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
        archiveBaseName.set(project.name)
        archiveVersion.set("")
        archiveClassifier.set("")
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
