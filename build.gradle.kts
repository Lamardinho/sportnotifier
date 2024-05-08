import org.springframework.boot.gradle.tasks.bundling.BootJar

// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-gradle-plugin
var springBootVersion: String = "3.2.4"

var lombokVersion: String = "1.18.30"

// https://mvnrepository.com/artifact/org.springframework/spring-web
var springWebVersion: String = "6.1.6"

// https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-starter-webmvc-ui
val openUiWebMvcVersion = "2.5.0"

// https://mvnrepository.com/artifact/org.apache.commons/commons-compress
val apacheCommonsCompressVersion = "1.26.1"

val h2DbVersion = "2.2.224"

// https://mvnrepository.com/artifact/org.postgresql/postgresql
val postgresqlVersion = "42.7.3"

val thymeleafVersion = "3.1.2.RELEASE"

val mapstructVersion = "1.4.2.Final"

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

    implementation("org.springframework.boot:spring-boot-gradle-plugin:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    // todo: Уже есть в какой то либе, но версия с уязвимостями. Указываем принудительно более новую:
    implementation("org.springframework:spring-web:$springWebVersion")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$openUiWebMvcVersion")
    // Уже есть в какой то либе, но версия с уязвимостями. Указываем принудительно более новую:
    implementation("org.apache.commons:commons-compress:$apacheCommonsCompressVersion")
    implementation("org.projectlombok:lombok:$lombokVersion")
    implementation("org.mapstruct:mapstruct:$mapstructVersion")
    //implementation("org.liquibase:liquibase-core:4.24.0")
    implementation("org.thymeleaf:thymeleaf:$thymeleafVersion")
    implementation("org.thymeleaf:thymeleaf-spring5:$thymeleafVersion")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf:$springBootVersion")

    runtimeOnly("org.postgresql:postgresql:$postgresqlVersion")
    runtimeOnly("com.h2database:h2:$h2DbVersion")

    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.mapstruct:mapstruct-processor:$mapstructVersion")

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

tasks.test {
    systemProperty("spring.profiles.active", "test")
}
