import org.springframework.boot.gradle.tasks.bundling.BootJar

var springBootVersion: String = "3.2.4"
var lombokVersion: String = "1.18.30"

plugins {
    java
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "com.lamardinho"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
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
    // https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-gradle-plugin
    implementation("org.springframework.boot:spring-boot-gradle-plugin:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-security:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")   // https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-starter-webmvc-ui
    // Уже есть в какой то либе, но там версия с уязвимостями. Указываем принудительно:
    implementation("org.apache.commons:commons-compress:1.26.1")     // https://mvnrepository.com/artifact/org.apache.commons/commons-compress

    implementation("org.liquibase:liquibase-core:4.24.0")
    runtimeOnly("org.postgresql:postgresql:42.7.3")      // https://mvnrepository.com/artifact/org.postgresql/postgresql
    runtimeOnly("com.h2database:h2:2.2.224")

    implementation("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testAnnotationProcessor("org.projectlombok:lombok:$lombokVersion")
}

tasks.withType<Test> {
    maxParallelForks = 1
    useJUnitPlatform()
}

tasks.named<BootJar>("bootJar") {
    archiveFileName = "sportnotifier.jar"
}
