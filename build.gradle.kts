plugins {
    kotlin("jvm") version "1.9.23"
    `maven-publish`
    java
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.reikaxubia"
            artifactId = "libgdxtts"
            version = "1.0.3"

            from(components["java"])
        }
    }
}

group = "com.github.auraxangelic"
version = "1.0.3"

dependencies {
    implementation("com.badlogicgames.gdx:gdx:${findProperty("gdxVersion")}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${findProperty("kotlinVersion")}")
    implementation(fileTree(mapOf("dir" to "src/main/resources/libs", "include" to listOf("*.jar"))))

    testImplementation("org.jetbrains.kotlin:kotlin-test")

    implementation ("com.github.auraxangelic:libgdx-tts:master-SNAPSHOT")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}