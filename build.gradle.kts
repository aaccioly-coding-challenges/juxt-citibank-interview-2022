plugins {
    kotlin("jvm") version "1.8.10"
    java
    id("com.adarshr.test-logger") version "3.2.0"
}

group = "pro.juxt"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.5.4")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
