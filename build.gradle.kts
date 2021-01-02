import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper

/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin library project to get you started.
 */

plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin on the JVM.
    id("org.jetbrains.kotlin.jvm").version("1.4.21")
    id("org.jetbrains.dokka") version "1.4.20"
    id("jacoco")
    id("java")
    // Upload jacoco coverage reports to coveralls
    id("com.github.kt3k.coveralls").version("2.8.2")
}

group = "io.hemin"
version = "0.9.0"

val junit5Version = "5.7.0"
val kotlinVersion = plugins.getPlugin(KotlinPluginWrapper::class.java).kotlinPluginVersion

// This might not be needed in the future, but as of present the default version bundled with the latest version of gradle does not work with Java 11
jacoco {
    toolVersion = "0.8.2"
}

repositories {
    // Use jcenter for resolving your dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    compile("com.google.guava:guava:27.1-jre")

    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junit5Version")
}

tasks {

    withType<Test> {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }

    withType<JacocoReport>().configureEach {
        reports {
            html.isEnabled = true
            xml.isEnabled = true
        }
    }

}

coveralls {
    sourceDirs = sourceDirs + "src/main/kotlin"
}
