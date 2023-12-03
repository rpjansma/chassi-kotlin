import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val newrelicVersion: String by project
val junitEnginnerVersion: String by project
val mockkVersion: String by project
val avroLibVersion: String by project
val kafkaAvroSerializer: String by project
val jacoco: String by project
val jakarta: String by project
val kotlinxCoroutinesCore: String by project
val oracleJdbcVersion: String by project
val springCloudVersion: String by project

plugins {
    application
    java
    jacoco
    id("org.springframework.boot") version "3.0.1"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
    kotlin("plugin.jpa") version "1.7.22"
    kotlin("plugin.serialization") version "1.5.20"
}

jacoco {
    toolVersion = jacoco
}

group = "user-api"
version = "$version"
java.sourceCompatibility = JavaVersion.VERSION_17
java.targetCompatibility = JavaVersion.VERSION_17


application {
    applicationName = "user-api"
}

dependencies {
    //SPRING
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.cloud:spring-cloud-starter-config:3.1.1")


    // JACKSON
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    //LOG
    implementation(platform("io.micrometer:micrometer-tracing-bom:1.0.0"))
    implementation("io.micrometer:micrometer-tracing")
    implementation("io.micrometer:micrometer-tracing-bridge-otel")

    // KOTLIN
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${kotlinxCoroutinesCore}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-slf4j:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    //SWAGGER
    implementation("org.springdoc:springdoc-openapi-starter-common:2.0.2")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2")
    implementation("io.swagger.core.v3:swagger-annotations-jakarta:${jakarta}")

    // TESTS
    testImplementation("io.projectreactor:reactor-test:3.4.4")
    testImplementation("io.mockk:mockk:1.11.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.1")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.7.1")
    testImplementation("com.ninja-squad:springmockk:3.0.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.0.1") {
        exclude(module = "mockito-core")
    }
    testImplementation("org.wiremock:wiremock:3.0.4")
    testImplementation("org.assertj:assertj-core:3.24.2")

    // DOMAIN
    implementation(project(":domain"))
}

tasks.distZip { enabled = false }
tasks.startScripts { enabled = false }
tasks.distTar { enabled = false }

tasks.withType<ProcessResources> {
    expand(project.properties)
}

tasks.getByName<Jar>("jar") {
    enabled = false
}

tasks.withType<JacocoReport> {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        xml.outputLocation.set(file("../build/reports/jacoco/domain.xml"))
        csv.required.set(false)
        html.required.set(true)
    }
    classDirectories.setFrom(
        sourceSets.main.get().output.asFileTree.matching {
            exclude(
                "**/mock"
            )
        }
    )
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                counter = "LINE"
                minimum = BigDecimal(0.00)
            }
        }
        rule {
            limit {
                counter = "BRANCH"
                minimum = BigDecimal(0.00)
            }
        }
    }
    mustRunAfter(tasks.jacocoTestReport)
    classDirectories.setFrom(
        sourceSets.main.get().output.asFileTree.matching {
            exclude(
                "**/mock"
            )
        }
    )
}
tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}
tasks.withType<Test> {
    testLogging {
        showStandardStreams = true
        events(org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
            org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
            org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED)
    }
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
    finalizedBy(tasks.jacocoTestCoverageVerification)
    systemProperty("spring.profiles.active", "test")
}