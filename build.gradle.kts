plugins {
    kotlin("jvm") version "1.7.20"
    java
    id("com.adarshr.test-logger") version "3.2.0"
}

group = "pro.juxt"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testImplementation("org.assertj:assertj-core:3.23.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
