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
    kotlin("jvm") version "1.4.31"
    id("org.jetbrains.dokka") version "1.4.30"
    id("org.jetbrains.kotlinx.binary-compatibility-validator") version "0.4.0"
    id("jacoco")
    `maven-publish`
    signing
    id("com.github.nbaztec.coveralls-jacoco") version "1.2.5"
    id("org.jmailen.kotlinter") version "3.3.0"
    id("io.gitlab.arturbosch.detekt") version "1.16.0"
    id("io.github.gradle-nexus.publish-plugin") version "1.0.0"
}

group = "dev.stalla"

val tagName = System.getenv("GITHUB_REF")?.let { ref ->
    if (ref.startsWith("ref/tags/")) {
        ref.substringAfterLast('/')
    } else {
        null
    }
}
version = tagName ?: "1.0.0-SNAPSHOT"

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
    testImplementation("org.reflections:reflections:0.9.12")
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

apiValidation {
    nonPublicMarkers.add("dev.stalla.util.InternalAPI")
}

val javadocJar by tasks.creating(Jar::class) {
    archiveClassifier.set("javadoc")
    from(tasks.dokkaJavadoc.get().outputs)
}

val sourcesJar by tasks.creating(Jar::class) {
    archiveClassifier.set("sources")
    from(kotlin.sourceSets.main.get().kotlin.srcDirs)
}

fun searchPropertyOrNull(name: String, vararg aliases: String): String? {
    fun searchEverywhere(name: String): String? =
        findProperty(name) as? String? ?: System.getenv(name)

    searchEverywhere(name)?.let { return it }

    aliases.forEach { alias ->
        searchEverywhere(alias)?.let { return it }
    }

    return null
}

nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
            username.set(searchPropertyOrNull("SONATYPE_USERNAME", "sonatypeUsername"))
            password.set(searchPropertyOrNull("SONATYPE_PASSWORD", "sonatypePassword"))
        }
    }
}

signing {
    val secretKey: String? = rootProject.file("secring.txt")
        .takeIf { it.exists() }
        ?.readText()
    val password: String? = searchPropertyOrNull("SIGNING_PASSWORD", "signingPassword")
    useInMemoryPgpKeys(secretKey, password)
}

publishing {
    publications.create<MavenPublication>(project.name) {
        artifact(javadocJar)
        artifact(sourcesJar)
        from(components["kotlin"])

        pom {
            name.set(project.name)
            description.set("RSS reading and writing library for podcasts, written in Kotlin and Java-friendly")
            url.set("https://github.com/mpgirro/stalla")
            scm {
                url.set("https://github.com/mpgirro/stalla.git")
            }
            licenses {
                license {
                    name.set("BSD 3-Clause License")
                    url.set("https://github.com/mpgirro/stalla/blob/master/LICENSE")
                }
            }
            developers {
                developer {
                    id.set("mpgirro")
                    name.set("Maximilian Irro")
                    url.set("https://github.com/mpgirro")
                }
                developer {
                    id.set("rock3r")
                    name.set("Sebastiano Poggi")
                    url.set("https://github.com/rock3r")
                }
            }
        }
        signing.sign(this)
    }
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf(
                "-Xopt-in=kotlin.RequiresOptIn",
                "-Xopt-in=kotlin.contracts.ExperimentalContracts",
                "-Xopt-in=dev.stalla.util.InternalAPI",
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

    afterEvaluate {
        // Needs to happen lazily as :detektMain is added late
        named("check") {
            dependsOn(named<Detekt>("detektMain"))
        }
    }

    register("outputVersionDebug") {
        doLast {
            logger.warn("Project version: ${project.version}")
            logger.warn("GITHUB_REF: ${System.getenv("GITHUB_REF")}")
        }
    }
}
