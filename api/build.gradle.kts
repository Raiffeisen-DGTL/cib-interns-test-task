import org.springframework.boot.gradle.tasks.bundling.BootJar

group = "cib.interns.test.task.api"

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

dependencies {
    implementation("org.springdoc:springdoc-openapi-ui:1.5.4")
    implementation("org.springdoc:springdoc-openapi-kotlin:1.5.4")
    implementation("org.springframework:spring-web")
}

tasks.getByName<BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}