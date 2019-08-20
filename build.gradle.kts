import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


val http4kVersion = "3.163.0"
val spekVersion = "2.0.5"

buildscript {
    repositories {
        mavenCentral()
    }
}

plugins {
    id("org.jetbrains.kotlin.jvm").version("1.3.41")
    id("application")
    id("com.github.johnrengelman.shadow").version("5.1.0")
    id("com.google.cloud.tools.jib").version("1.4.0")
}

repositories {
    jcenter()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("org.glassfish.jaxb:jaxb-runtime:2.3.2")
    implementation("org.glassfish.jaxb:txw2:2.3.2")
    implementation("com.sun.xml.ws:jaxws-rt:2.3.2")

    implementation("com.pi4j:pi4j-core:1.1")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.17")
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:$spekVersion")
    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:$spekVersion")
    testImplementation("io.mockk:mockk:1.9.3")
    testImplementation(gradleTestKit())
}


tasks.withType<Test> {
    useJUnitPlatform {
        includeEngines("spek2")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClassName = "io.github.aworley1.rpi_train_checker.MainKt"
}

jib {
    container {
        mainClass = application.mainClassName
    }
    from {
        image = "azul/zulu-openjdk-alpine:12"
    }
    to {
        image = "aworley1/rpi-train-checker:latest"
        auth {
            username = System.getenv("DOCKER_HUB_USERNAME")
            password = System.getenv("DOCKER_HUB_PASSWORD")
        }
        tags = setOf(System.getenv("CI_COMMIT_SHORT_SHA"))
    }
}

