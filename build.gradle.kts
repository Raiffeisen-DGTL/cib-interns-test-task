import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val exposedVersion: String by project
val ktorVersion: String by project
val postgresqlVersion: String by project

plugins {
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.serialization") version "1.5.31"
    id("com.github.johnrengelman.shadow") version "7.0.0"
    application
}

group = "com.vitekkor"
version = "1.0"

repositories {
    jcenter()
    mavenCentral()
    maven("https://repo.panda-lang.org/releases")
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("io.ktor:ktor-server-test-host:$ktorVersion")
    testImplementation("com.h2database:h2:1.4.199")
    implementation("io.ktor:ktor-serialization:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.postgresql:postgresql:$postgresqlVersion")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("com.vitekkor.socksShop.ServerKt")
}

tasks{
    shadowJar {
        manifest {
            attributes(Pair("Main-Class", "com.vitekkor.socksShop.ServerKt"))
        }
    }
}