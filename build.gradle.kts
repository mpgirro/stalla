import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()
//        maven { url = "https://kotlin.bintray.com/kotlinx/" }
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
    id("org.jetbrains.kotlin.jvm").version("1.4.30")
    id("org.jetbrains.dokka") version "1.4.20"
    id("jacoco")
    id("java")
    id("com.github.nbaztec.coveralls-jacoco").version("1.2.5")
    id("org.jmailen.kotlinter") version "3.3.0"
    id("com.eden.orchidPlugin") version "0.21.0"
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

    val orchid_version = "0.21.0"
    implementation("io.github.javaeden.orchid:OrchidCore:$orchid_version")
    orchidCompile("io.github.javaeden.orchid:OrchidCore:$orchid_version")


    orchidRuntime("io.github.javaeden.orchid:OrchidPages:$orchid_version")
    orchidRuntime("io.github.javaeden.orchid:OrchidPluginDocs:$orchid_version")
    /*
    orchidRuntime("io.github.javaeden.orchid:OrchidPosts:$orchid_version")
    orchidRuntime("io.github.javaeden.orchid:OrchidWiki:$orchid_version")
    orchidRuntime("io.github.javaeden.orchid:OrchidNetlify:$orchid_version")
    orchidRuntime("io.github.javaeden.orchid:OrchidNetlifyCMS:$orchid_version")


    orchidRuntime("io.github.javaeden.orchid:OrchidWritersBlocks:$orchid_version")
    orchidRuntime("io.github.javaeden.orchid:OrchidSyntaxHighlighter:$orchid_version")
    orchidRuntime("io.github.javaeden.orchid:OrchidTaxonomies:$orchid_version")
    orchidRuntime("io.github.javaeden.orchid:OrchidFutureImperfect:$orchid_version")
    orchidRuntime("io.github.javaeden.orchid:OrchidAsciidoc:$orchid_version")
    */
    orchidRuntime("io.github.javaeden.orchid:OrchidEditorial:$orchid_version")
    orchidRuntime("io.github.javaeden.orchid:OrchidSourceDoc:$orchid_version")
    orchidRuntime("io.github.javaeden.orchid:OrchidKotlindoc:$orchid_version")
    orchidRuntime("io.github.javaeden.orchid:OrchidSearch:$orchid_version")
    orchidRuntime("io.github.javaeden.orchid:OrchidGithub:$orchid_version")

    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junit5Version")
    testImplementation("com.willowtreeapps.assertk:assertk:0.23")
    testImplementation("org.xmlunit:xmlunit-core:2.8.2")
}

kotlin {
    explicitApi()
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf("-Xopt-in=kotlin.RequiresOptIn", "-Xopt-in=dev.stalla.util.InternalApi")
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
}

orchid {
 //   baseUrl = "https://stalla.dev"        // a baseUrl prepended to all generated links. Can also be set as `site.baseUrl` in `config.yml` Defaults to '/'
    runTask = "build"                     // specify a task to run with 'gradle orchidRun'
}
