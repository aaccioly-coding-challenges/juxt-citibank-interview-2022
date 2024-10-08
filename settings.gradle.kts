rootProject.name = "juxt-citibank-interview-2022"

plugins {
    id("com.gradle.enterprise") version ("3.12.3")
}

gradleEnterprise {
    if (System.getenv("CI") != null) {
        buildScan {
            publishAlways()
            termsOfServiceUrl = "https://gradle.com/terms-of-service"
            termsOfServiceAgree = "yes"
        }
    }
}

