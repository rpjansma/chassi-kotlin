import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val archVersion: String by project
val jacoco: String by project
val junit: String by project
val mockk: String by project
val kotlinxCoroutinesCore: String by project

plugins {
    jacoco
    kotlin("jvm") version "1.7.22"
}

jacoco {
    toolVersion = jacoco
}

group = "ps.investments.stockmarket.dividends.events.domain"
version = "$version"
java.sourceCompatibility = JavaVersion.VERSION_17
java.targetCompatibility = JavaVersion.VERSION_17

dependencies {
    implementation("org.springframework:spring-context:5.3.24")
    implementation("org.springframework:spring-tx:5.3.24")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${kotlinxCoroutinesCore}")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions:1.1.3")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.11.4")

    // TESTS
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.0.1") {
        exclude(module = "mockito-core")
    }
    testImplementation("com.ninja-squad:springmockk:3.0.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junit")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junit")
    testImplementation("io.mockk:mockk:$mockk")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    ignoreFailures = true
    testLogging {
        showStandardStreams = true
        events(org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
            org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
            org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED)
    }
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
    finalizedBy(tasks.jacocoTestCoverageVerification)
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
                "ps/investments/stockmarket/dividends/events/domain/entity/",
                "ps/investments/stockmarket/dividends/events/domain/exception/"
            )
        }
    )
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                counter = "LINE"
                minimum = BigDecimal(0.80)
            }
        }
        rule {
            limit {
                counter = "BRANCH"
                minimum = BigDecimal(0.80)
            }
        }
    }
    mustRunAfter(tasks.jacocoTestReport)
    classDirectories.setFrom(
        sourceSets.main.get().output.asFileTree.matching {
            exclude(
                "ps/investments/stockmarket/dividends/events/domain/entity/",
                "ps/investments/stockmarket/dividends/events/domain/exception/"
            )
        }
    )
}
