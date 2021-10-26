plugins {
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

dependencies {
    implementation(project(":database"))
    implementation(project(":api"))

    implementation("org.springframework.boot:spring-boot-starter-web")

    //Swagger
    implementation("org.springdoc:springdoc-openapi-ui:1.5.9")
    implementation("org.springdoc:springdoc-openapi-webmvc-core:1.5.9")

    //database
    //implementation("org.liquibase:liquibase-core")
    implementation("org.postgresql:postgresql")

    implementation("org.hibernate.validator:hibernate-validator:6.0.13.Final")
    implementation("jakarta.validation:jakarta.validation-api:2.0.2")

    // Mapstruct
    kapt("org.mapstruct:mapstruct-processor:1.4.0.Final")
    implementation("org.mapstruct:mapstruct-processor:1.4.0.Final")
    implementation("org.mapstruct:mapstruct:1.4.0.Final")
}
repositories {
    mavenCentral()
}