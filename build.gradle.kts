import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter {
            content {
                includeGroup("org.jetbrains.kotlinx")
            }
        }
    }

    val ktlintVersion = "0.40.0"
    configurations.classpath {
        resolutionStrategy {
            force(
                "com.pinterest.ktlint:ktlint-core:$ktlintVersion",
                "com.pinterest.ktlint:ktlint-reporter-checkstyle:$ktlintVersion",
                "com.pinterest.ktlint:ktlint-reporter-json:$ktlintVersion",
                "com.pinterest.ktlint:ktlint-reporter-html:$ktlintVersion",
                "com.pinterest.ktlint:ktlint-reporter-plain:$ktlintVersion",
                "com.pinterest.ktlint:ktlint-ruleset-experimental:$ktlintVersion",
                "com.pinterest.ktlint:ktlint-ruleset-standard:$ktlintVersion"
            )
        }
    }
}

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.4.30"
    id("org.jetbrains.dokka") version "1.4.20"
    id("jacoco")
    id("java")
    id("com.github.nbaztec.coveralls-jacoco") version "1.2.5"
    id("org.jmailen.kotlinter") version "3.3.0"
    id("io.gitlab.arturbosch.detekt") version "1.16.0-RC2"
}

group = "dev.stalla"
version = "0.10.0"

val junit5Version = "5.7.0"
val kotlinVersion = plugins.getPlugin(KotlinPluginWrapper::class.java).kotlinPluginVersion

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junit5Version")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junit5Version")
    testImplementation("com.willowtreeapps.assertk:assertk:0.23")
    testImplementation("org.xmlunit:xmlunit-core:2.8.2")
}

kotlin {
    explicitApi()
}

detekt {
    input = files("src/main/java", "src/main/kotlin")
    config = files("config/detekt/config.yml")
    buildUponDefaultConfig = true
    reports {
        sarif {
            enabled = true
        }
    }
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf(
                "-Xopt-in=kotlin.RequiresOptIn",
                "-Xopt-in=kotlin.contracts.ExperimentalContracts",
                "-Xopt-in=dev.stalla.util.InternalApi",
                "-Xopt-in=dev.stalla.util.ExperimentalAPI"
            )
        }
    }

    withType<Test> {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }

    withType<JacocoReport> {
        reports {
            html.isEnabled = true
            xml.isEnabled = true
        }
    }

    withType<Detekt> {
        // Required for type resolution
        jvmTarget = "1.8"
    }

    named("check", Task::class) {
        // Run the type resolution aware (and experimental) Detekt task
        dependsOn.add(named("detektMain"))
    }
}
