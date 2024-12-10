plugins {
    kotlin("jvm") version "1.9.23"
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "8.1.1"
}
sourceSets {
    main {
        java {
            setSrcDirs(listOf("src/main/kotlin"))
        }
    }
}

group = "com.github.auraxangelic"
version = "1.0.15"

dependencies {
    implementation("com.badlogicgames.gdx:gdx:${findProperty("gdxVersion")}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${findProperty("kotlinVersion")}")
    implementation(fileTree("src/main/resources/libs") {
        include("*.jar")
    })
}

tasks {
    shadowJar {
        // Explicitly include local JARs
        from(fileTree("src/main/resources/libs") {
            include("*.jar")
        })

        // Include your project's compiled classes
        from(sourceSets.main.get().output)

        // Avoid including runtime dependencies globally to prevent duplicates
        configurations = listOf()

        archiveBaseName.set("libgdx-tts")
        archiveClassifier.set("")
    }

    // Disable the default jar task to prevent conflicts
    jar {
        enabled = false
    }

    // Ensure shadowJar runs as part of the build lifecycle
    build {
        dependsOn(shadowJar)
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = "libgdx-tts" // Artifact name
            version = project.version.toString()

            // Use the shadowJar artifact for publishing
            artifact(tasks.shadowJar.get())
        }
    }
}
tasks.test {
    useJUnitPlatform()
}
