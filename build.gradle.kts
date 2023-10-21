var springBootVersion: String = "2.7.0"
var lombokVersion: String = "1.18.30"

plugins {
    java
    id("org.springframework.boot") version "2.7.0"
    id("io.spring.dependency-management") version "1.1.3"
}

group = "com.lamardinho"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_11
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-security:$springBootVersion")
    implementation("org.springdoc:springdoc-openapi-ui:1.7.0")      // https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-ui

    implementation("org.liquibase:liquibase-core:4.24.0")
    implementation("org.postgresql:postgresql:42.6.0")      // https://mvnrepository.com/artifact/org.postgresql/postgresql
    runtimeOnly("com.h2database:h2:2.2.224")

    implementation("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
    testAnnotationProcessor("org.projectlombok:lombok:$lombokVersion")
}

tasks.withType<Test> {
    maxParallelForks = 1
    useJUnitPlatform()
}
