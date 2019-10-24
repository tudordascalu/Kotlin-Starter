plugins {
    `build-scan`
    `maven-publish`
    `application`
    id("org.jetbrains.kotlin.jvm") version "1.3.50"
    id("org.jetbrains.dokka") version "0.9.17"
}

buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"

    publishAlways()
}

repositories {
    mavenLocal()
    mavenCentral()
}

application {
    mainClassName = "rest.api.AuthAppKt"
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    api("io.dropwizard:dropwizard-core:0.8.0")
    implementation("io.jsonwebtoken:jjwt:0.7.0")
    testImplementation("junit:junit:4.12")
    compile(group="postgresql", name="postgresql", version="9.1-901-1.jdbc4")
}

tasks.dokka {
    outputFormat = "html"
    outputDirectory = "$buildDir/javadoc"
}

group = "org.example"
version = "0"

publishing {
    publications {
        create<MavenPublication>("default") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            url = uri("$buildDir/repository")
        }
    }
}

tasks.named<JavaExec>("run") {
    args("server", "src/config/local.yml")
}

