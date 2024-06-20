import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "1.6.21"
	id("org.springframework.boot") version "2.6.6"
	id("io.spring.dependency-management") version "1.0.14.RELEASE"
}

group = "kotlin"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	// Spring Boot
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.cloud:spring-cloud-starter-sleuth:3.1.9")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("org.springframework.boot:spring-boot-starter-webflux")

	// Kotlin
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.6.0")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation ("org.jetbrains.kotlin:kotlin-reflect")

	// Swagger
	implementation("org.springdoc:springdoc-openapi-starter-common:2.0.2")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2")
	implementation("io.swagger.core.v3:swagger-annotations-jakarta:2.2.7")

	// Database
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.postgresql:postgresql")

	// SLF4J and Logging
	implementation("org.slf4j:slf4j-api:1.7.36")
	implementation("org.slf4j:log4j-over-slf4j:1.7.36")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
