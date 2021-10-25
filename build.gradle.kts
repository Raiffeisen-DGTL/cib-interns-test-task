plugins {
    java
    idea
    kotlin("jvm") version "1.5.21" apply false
    kotlin("plugin.spring") version "1.5.21" apply false
    kotlin("plugin.jpa") version "1.5.21" apply false
    kotlin("kapt") version "1.5.21" apply false
    id("org.springframework.boot") version "2.5.3" apply false
    id("io.spring.dependency-management") version "1.0.11.RELEASE" apply false
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "idea")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.kapt")


    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.31")
        implementation("org.jetbrains.kotlin:kotlin-reflect:1.1.51")
    }
}