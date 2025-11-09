plugins {
    kotlin("jvm") version "2.2.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.12.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation(kotlin("test"))
    testImplementation("org.assertj:assertj-core:3.27.3")
    testImplementation("io.mockk:mockk:1.13.14")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}