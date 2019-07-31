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
//    id("application")
}

repositories {
    jcenter()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("ch.qos.logback:logback-classic:1.2.3")

    implementation("org.http4k:http4k-core:$http4kVersion")
    implementation("org.http4k:http4k-client-okhttp:$http4kVersion")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.17")
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:$spekVersion")
    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:$spekVersion")
    testRuntimeOnly("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.http4k:http4k-server-jetty:$http4kVersion")
    testImplementation(gradleTestKit())
}


tasks.withType<Test> {
    useJUnitPlatform {
        includeEngines("spek2")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "12"
}

