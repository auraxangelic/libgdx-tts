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
version = "1.0.25"

dependencies {
    implementation("com.badlogicgames.gdx:gdx:${findProperty("gdxVersion")}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${findProperty("kotlinVersion")}")
    implementation(fileTree("src/main/resources/libs") {
        include("*.jar")
    })
}

tasks {
    shadowJar {
        duplicatesStrategy = DuplicatesStrategy.WARN

        // Properly include your compiled classes and resources
        from(sourceSets.main.get().output)

        // Include dependencies
        configurations = listOf(project.configurations.runtimeClasspath.get())

        // Resource handling
        from("src/main/resources") {
            include("**/*.bin")
            include("**/*.idx")
            include("**/*.txt")
        }

        // Common exclusions
        exclude("META-INF/*.SF")
        exclude("META-INF/*.DSA")
        exclude("META-INF/*.RSA")
        exclude("com/badlogic/**")
        exclude("kotlin/**")
        exclude("org/intellij/**")
        exclude("org/jetbrains/**")
        exclude("META-INF/kotlin**")
        exclude("META-INF/maven/**")
        exclude("META-INF/versions/**")

        mergeServiceFiles()

        // Archive details
        archiveBaseName.set("libgdx-tts")
        archiveClassifier.set("")
    }

    jar {
        enabled = false
    }

    build {
        dependsOn(shadowJar)
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = "libgdx-tts"
            version = project.version.toString()
            artifact(tasks.shadowJar.get())
        }
    }
}
