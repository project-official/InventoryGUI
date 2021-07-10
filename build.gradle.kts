plugins {
    kotlin("jvm") version "1.5.20"
    `maven-publish`

    //`java-library`
    //signing
}

group = properties["pluginGroup"]!!
version = properties["pluginVersion"]!!

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    implementation(kotlin("stdlib"))
    compileOnly("com.destroystokyo.paper:paper-api:1.16.5-R0.1-SNAPSHOT")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

val shade = configurations.create("shade")
shade.extendsFrom(configurations.implementation.get())

tasks {
    getByName<Test>("test") {
        useJUnitPlatform()
    }

    create<Jar>("sourceJar") {
        archiveClassifier.set("source")
        from(sourceSets["main"].allSource)
    }

    jar {
        from(shade.map { if (it.isDirectory) it else zipTree(it) })
    }
}

/*
tasks.register<Jar>("javadocJar") {
    archiveClassifier.set("javadoc")
    from(tasks.javadoc.get().destinationDir)
}
*/

publishing {
    publications {
        create<MavenPublication>(rootProject.name) {
            from(components["java"])
            artifact(tasks["sourceJar"])
            /*
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
            */
        }
    }
}

/*
signing {
    val pgpSigningKey: String? by project
    val pgpSigningPwd: String? by project
    useInMemoryPgpKeys(pgpSigningKey, pgpSigningPwd)
    sign(publishing.publications["mavenJava"])
}
 */