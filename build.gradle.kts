plugins {
    kotlin("jvm") version "1.9.0"
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
    val kotestVersion = "5.6.2"

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
