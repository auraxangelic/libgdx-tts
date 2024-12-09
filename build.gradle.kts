plugins {
    kotlin("jvm") version "1.9.23"
    `maven-publish`
}

group = "com.github.auraxangelic"
version = "1.0.7"

dependencies {
    implementation("com.badlogicgames.gdx:gdx:${findProperty("gdxVersion")}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${findProperty("kotlinVersion")}")
    implementation(fileTree(mapOf("dir" to "src/main/resources/libs", "include" to listOf("*.jar"))))

    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.withType<Jar> {
    archiveBaseName.set("libgdx-tts") // Name of the artifact
    from(sourceSets.main.get().output) // Include compiled classes
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }) // Include runtime dependencies
}

tasks.test {
    useJUnitPlatform()
}
