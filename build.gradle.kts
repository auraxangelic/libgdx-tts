plugins {
    kotlin("jvm") version "1.9.23"
    `maven-publish`
}

group = "com.github.auraxangelic"
version = "1.0.5"

dependencies {
    implementation("com.badlogicgames.gdx:gdx:${findProperty("gdxVersion")}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${findProperty("kotlinVersion")}")
    implementation(fileTree(mapOf("dir" to "src/main/resources/libs", "include" to listOf("*.jar"))))

    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(8)
}
