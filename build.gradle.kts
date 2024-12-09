plugins {
    kotlin("jvm") version "1.9.23"
    `maven-publish`
}

group = "com.github.auraxangelic"
version = "1.0.9"

dependencies {
    implementation("com.badlogicgames.gdx:gdx:${findProperty("gdxVersion")}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${findProperty("kotlinVersion")}")
    implementation(fileTree(mapOf("dir" to "src/main/resources/libs", "include" to listOf("*.jar"))))

    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.withType<Jar> {
    archiveBaseName.set("libgdx-tts") // Name of your artifact
    from(sourceSets.main.get().output)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = project.name // Defaults to project name
            version = project.version.toString()
            from(components["java"]) // Publish the Java component
        }
    }
}

tasks.test {
    useJUnitPlatform()
}
