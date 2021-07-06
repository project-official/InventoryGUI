plugins {
    kotlin("jvm") version "1.5.20"
    java

    `maven-publish`
    signing
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

tasks {
    create<Jar>("javadocJar") {
        archiveClassifier.set("javadoc")
        from(javadoc)
    }

    create<Jar>("sourceJar") {
        archiveClassifier.set("source")
        from(sourceSets["main"].allSource)
    }
}

publishing {
    publications {
        create<MavenPublication>(rootProject.name) {
            from(components["java"])
            artifacts {
                tasks["javadocJar"]
                tasks["sourceJar"]
            }

            repositories {
                mavenLocal()

                maven {
                    name = "central"

                    credentials.runCatching {
                        val nexusUsername = project.properties["nexus_username"].toString()
                        val nexusPassword = project.properties["nexus_password"].toString()

                        username = nexusUsername
                        password = nexusPassword
                    }.onFailure {
                        logger.warn("Failed to load nexus credentials, Check the gradle.properties")
                    }

                    url = uri(
                        if ("SNAPSHOT" in version) {
                            "https://s01.oss.sonatype.org/content/repositories/snapshots/"
                        } else {
                            "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
                        }
                    )
                }
            }

            pom {
                name.set(project.name)
                description.set("Minecraft paper plugin library")
                url.set("https://github.com/ProjectTL12345/InventoryGUI")

                licenses {
                    license {
                        name.set("GNU General Public License version 3.0v")
                        url.set("https://opensource.org/licenses/GPL-3.0")
                    }
                }

                developers {
                    developer {
                        id.set("ProjectTL12345")
                        name.set("Project_TL")
                        email.set("me@projecttl.net")
                    }
                }

                scm {
                    connection.set("scm:git:git://github.com/ProjectTL12345/InventoryGUI.git")
                    developerConnection.set("scm:git:ssh://github.com:ProjectTL12345/InventoryGUI.git")
                    url.set("https://github.com/ProjectTL12345/InventoryGUI")
                }
            }
        }
    }
}

signing {
    isRequired = true
    sign(tasks["javadocJar"], tasks["sourceJar"])
    sign(publishing.publications[rootProject.name])
}