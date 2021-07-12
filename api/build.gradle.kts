plugins {
    `maven-publish`
    `java-library`

    signing
}

group = rootProject.group
version = rootProject.version

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    implementation(kotlin("stdlib"))
    compileOnly("com.destroystokyo.paper:paper-api:1.16.5-R0.1-SNAPSHOT")
}

tasks {
    register<Jar>("javadocJar") {
        archiveClassifier.set("javadoc")
        from(javadoc)
    }

    register<Jar>("sourceJar") {
        archiveClassifier.set("source")
        from(sourceSets["main"].allSource)
    }
}

publishing {
    publications {
        create<MavenPublication>(rootProject.name) {
            from(components["java"])
            artifact(tasks["sourceJar"])
            artifact(tasks["javadocJar"])

            val mavenUploadUser: String by project
            val mavenUploadPwd: String by project

            repositories {
                maven {
                    name = "MavenCentral"

                    val releasesRepoUrl = "https://oss.sonatype.org/service/local/staging/deploy/maven2"
                    val snapshotsRepoUrl = "https://oss.sonatype.org/content/repositories/snapshots"

                    url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)

                    credentials {
                        username = mavenUploadUser
                        password = mavenUploadPwd
                    }
                }
            }
            pom {
                name.set(rootProject.name)
                description.set("")
                url.set("https://github.com/ProjectTL12345/InventoryGUI/")
                developers {
                    developer {
                        name.set("ProjectTL12345")
                        email.set("me@projecttl.net")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/ProjectTL12345/InventoryGUI.git")
                    developerConnection.set("scm:git:https://github.com/ProjectTL12345/InventoryGUI.git")
                    url.set("https://github.com/ProjectTL12345/")
                }
            }
        }
    }
}


signing {
    val pgpSigningKey: String? by project
    val pgpSigningPwd: String? by project

    useInMemoryPgpKeys(pgpSigningKey, pgpSigningPwd)
    sign(publishing.publications[rootProject.name])
    sign(tasks["sourceJar"], tasks["javadocJar"])
}