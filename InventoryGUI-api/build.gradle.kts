plugins {
    `maven-publish`
    signing
}

tasks {
    javadoc {
        options.encoding = "UTF-8"
    }

    create<Jar>("sourcesJar") {
        archiveClassifier.set("sources")
        from(sourceSets["main"].allSource)
    }

    create<Jar>("javadocJar") {
        archiveClassifier.set("javadoc")
        dependsOn("dokkaHtml")
        from("$buildDir/dokka/html")
    }
}

publishing {
    publications {
        create<MavenPublication>("${rootProject.name}-api") {
            from(components["java"])
            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])

            repositories {
                maven {
                    name = "MavenCentral"
                    val releasesRepoUrl = "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
                    val snapshotsRepoUrl = "https://s01.oss.sonatype.org/content/repositories/snapshots/"
                    url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)

                    credentials.runCatching {
                        val nexusUsername: String by project
                        val nexusPassword: String by project
                        username = nexusUsername
                        password = nexusPassword
                    }
                }
            }

            pom {
                name.set(rootProject.name)
                description.set("This is minecraft gui library")
                url.set("https://github.com/DevProject/InventoryGUI")
                licenses {
                    license {
                        name.set("GNU General Public License Version 3")
                        url.set("https://www.gnu.org/licenses/gpl-3.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("DevProject04")
                        name.set("Dev_Project")
                        email.set("me@projecttl.net")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/DevProject04/InventoryGUI.git")
                    developerConnection.set("scm:git:https://github.com/DevProject04/InventoryGUI.git")
                    url.set("https://github.com/DevProject04/InventoryGUI.git")
                }
            }
        }
    }
}

signing {
    isRequired = true
    sign(publishing.publications["${rootProject.name}-api"])
}
