plugins {
    kotlin("jvm") version "1.9.23"
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "com.github.auraxangelic"
version = "1.0.10"

dependencies {
    implementation("com.badlogicgames.gdx:gdx:${findProperty("gdxVersion")}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${findProperty("kotlinVersion")}")
    implementation(fileTree(mapOf("dir" to "src/main/resources/libs", "include" to listOf("*.jar"))))

    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks {
    shadowJar {
        archiveBaseName.set("libgdx-tts")
        archiveClassifier.set("")
        from(sourceSets.main.get().output)
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()

            artifact(tasks.shadowJar.get()) // Use the fat JAR
        }
    }
}

tasks.test {
    useJUnitPlatform()
}
