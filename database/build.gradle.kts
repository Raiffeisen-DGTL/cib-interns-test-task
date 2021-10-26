plugins {

    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//    implementation("org.springframework.data:spring-data-jpa")
//    implementation("jakarta.persistence:jakarta.persistence-api:3.0.0")
//    implementation("org.hibernate:hibernate-core:5.6.0.Final")



    implementation(kotlin("stdlib"))
}
repositories {
    mavenCentral()
}