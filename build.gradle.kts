import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val projectGroup: String by project
val applicationVersion: String by project
val javaVersion: String by project

plugins {
    kotlin("jvm")
    kotlin("plugin.spring") apply false
    id("org.springframework.boot") apply false
    id("io.spring.dependency-management")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(javaVersion)
    }
}

allprojects {
    group = projectGroup
    version = applicationVersion

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
}

val subProjects = arrayOf(":coin-producer", ":coin-consumer", ":transformer-server")

subProjects.forEach { subProject ->
    project(subProject){
        dependencies {
            implementation("org.springframework.boot:spring-boot-starter-web")
            implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
            implementation("org.jetbrains.kotlin:kotlin-reflect")
            implementation("org.springframework.cloud:spring-cloud-stream")
            implementation("org.springframework.cloud:spring-cloud-stream-binder-rabbit")
            implementation("org.springframework.cloud:spring-cloud-stream-binder-kafka")
            testImplementation("org.springframework.boot:spring-boot-starter-test")
            testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
            testImplementation("org.springframework.cloud:spring-cloud-stream-test-binder")
            testRuntimeOnly("org.junit.platform:junit-platform-launcher")
        }

        java {
            toolchain {
                languageVersion = JavaLanguageVersion.of(javaVersion)
            }
        }

        dependencyManagement {
            imports {
                mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
            }
        }

        kotlin {
            compilerOptions {
                freeCompilerArgs.addAll("-Xjsr305=strict")
            }
        }

        tasks.withType<Test> {
            useJUnitPlatform()
        }
    }
}
