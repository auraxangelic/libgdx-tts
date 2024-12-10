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
version = "1.0.14"

dependencies {
    implementation("com.badlogicgames.gdx:gdx:${findProperty("gdxVersion")}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${findProperty("kotlinVersion")}")
    implementation(fileTree(mapOf("dir" to "src/main/resources/libs", "include" to listOf("*.jar"))))
}

tasks {
    shadowJar {
        include("com/reikaxubia/libgdxtts/**")
        include("com/sun/speech/**")
        include("javax/speech/**")
        include("de/dfki/lt/freetts/**")

        archiveBaseName.set("libgdx-tts")
        archiveClassifier.set("")
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
