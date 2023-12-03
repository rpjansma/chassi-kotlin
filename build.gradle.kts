val artifactoryGradleUrl: String by project
val artifactoryMavenUrl: String by project
val artifactoryArchUrl: String by project

allprojects {
    repositories {
        mavenCentral()
    }
}