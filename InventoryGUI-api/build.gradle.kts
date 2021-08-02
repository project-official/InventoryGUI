plugins {
    id("com.github.johnrengelman.shadow") version "7.0.0"
    `maven-publish`
    signing
}

group = rootProject.group
version = rootProject.version

tasks {
    javadoc {
        options.encoding = "UTF-8"
    }

    create<Jar>("sourcesJar") {
        archiveClassifier.set("sources")
        from(sourceSets["main"].allSource)
    }
}

publishing {
    publications {
        create<MavenPublication>("${rootProject.name}-api") {
            from(components["java"])
            artifact(tasks["sourcesJar"])

            repositories {
                maven {
                    name = "MavenCentral"
                    val releasesRepoUrl = "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
                    val snapshotsRepoUrl = "https://s01.oss.sonatype.org/content/repositories/snapshots/"
                    url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)

                    credentials.runCatching {
                        val MAVEN_UPLOAD_USER: String by project
                        val MAVEN_UPLOAD_PWD: String by project
                        username = MAVEN_UPLOAD_USER
                        password = MAVEN_UPLOAD_PWD
                    }
                }

                pom {
                    name.set(rootProject.name)
                    description.set("This is minecraft gui library")
                    url.set("https://github.com/ProjectTL12345/InventoryGUI")
                    licenses {
                        license {
                            name.set("GNU GENERAL PUBLIC LICENSE Version 3")
                            url.set("https://www.gnu.org/licenses/gpl-3.0.txt")
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
                        connection.set("scm:git:https://github.com/ProjetTL12345/InventoryGUI.git")
                        developerConnection.set("scm:git:https://github.com/ProjetTL12345/InventoryGUI.git")
                        url.set("https://github.com/ProjetTL12345/InventoryGUI.git")
                    }
                }
            }
        }
    }
}

signing {
    val PGP_SIGNING_KEY: String by project
    val PGP_SIGNING_PASSWORD: String by project
    useInMemoryPgpKeys(PGP_SIGNING_KEY, PGP_SIGNING_PASSWORD)
    sign(tasks["sourcesJar"])
    sign(publishing.publications["${rootProject.name}-api"])
}