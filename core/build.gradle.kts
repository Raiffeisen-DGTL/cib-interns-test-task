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
}
repositories {
    mavenCentral()
}