plugins {
    kotlin("jvm") version "1.9.23"
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "com.github.auraxangelic"
version = "1.0.11"

dependencies {
    implementation("com.badlogicgames.gdx:gdx:${findProperty("gdxVersion")}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${findProperty("kotlinVersion")}")
    implementation(fileTree(mapOf("dir" to "src/main/resources/libs", "include" to listOf("*.jar"))))

    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks {
    shadowJar {
        archiveBaseName.set("libgdx-tts") // Name of the fat JAR
        archiveClassifier.set("") // Ensure no "-all" suffix
        from(sourceSets.main.get().output)
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
