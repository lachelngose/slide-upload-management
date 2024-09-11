plugins {
    val kotlinVersion = "1.8.21"
    id("org.springframework.boot") version "3.1.4"
    kotlin("plugin.jpa") version kotlinVersion
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("kapt") version kotlinVersion
    kotlin("plugin.allopen") version "1.9.22"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    // Spring Boot, WebFlux
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    // Reactor Kotlin Extensions, Coroutines
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    // Jackson module for Kotlin
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // MySQL JDBC Driver
    runtimeOnly("com.mysql:mysql-connector-j")

    // QueryDSL
    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")

    // AWS S3
    implementation(platform("io.awspring.cloud:spring-cloud-aws-dependencies:3.1.0"))
    implementation("io.awspring.cloud:spring-cloud-aws-starter-s3")

    // Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.mockk:mockk:1.13.8")
}
