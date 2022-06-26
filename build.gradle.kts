import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    base
    id("org.springframework.boot")
    id("io.spring.dependency-management")

    id("java")

    kotlin("jvm")
    kotlin("plugin.spring")

    id("com.ewerk.gradle.plugins.querydsl") version "1.0.10" apply false

    jacoco
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    group = "com.gssg"
    version = "0.0.1-SNAPSHOT"

    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "java")

    apply(plugin = "kotlin")
    apply(plugin = "kotlin-spring")

    apply(plugin = "jacoco")

    java.sourceCompatibility = JavaVersion.VERSION_11
    java.targetCompatibility = JavaVersion.VERSION_11

    the<io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension>().apply {
        val springCloudVersion: String by project
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
        }
    }

    ext {
        set("p6spyVersion", "1.7.1")
        set("openapiVersion", "1.5.9")
    }

    jacoco {
        toolVersion = "0.8.7"
    }

    dependencies {
        val openapiVersion: String by project
        val p6spyVersion: String by project

        implementation(kotlin("stdlib-jdk8"))
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

        implementation("org.springframework.boot:spring-boot-starter-actuator")
        implementation("org.springframework.boot:spring-boot-starter-security")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-validation")
        implementation("org.springframework.boot:spring-boot-starter-webflux")

        implementation("io.jsonwebtoken:jjwt-api:0.11.2")
        runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.2")
        runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.2")

        implementation("org.springdoc:springdoc-openapi-ui:${openapiVersion}")

        implementation("commons-codec:commons-codec")
        implementation("org.apache.commons:commons-lang3")

        implementation("com.github.gavlyukovskiy:p6spy-spring-boot-starter:${p6spyVersion}")

        compileOnly("org.projectlombok:lombok")
        runtimeOnly("com.h2database:h2")
        runtimeOnly("mysql:mysql-connector-java")
        annotationProcessor("org.projectlombok:lombok")

        developmentOnly("org.springframework.boot:spring-boot-devtools")

        testImplementation("org.springframework.boot:spring-boot-starter-test") {
            exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        }
        testImplementation("org.springframework.security:spring-security-test")
    }

    val jar: Jar by tasks
    val bootJar: BootJar by tasks

    bootJar.enabled = false
    jar.enabled = true

    tasks.test {
        useJUnitPlatform()
        extensions.configure(JacocoTaskExtension::class) {
            setDestinationFile(file("$buildDir/jacoco/jacoco.exec"))
        }

        finalizedBy("jacocoTestReport")
    }

    tasks.jacocoTestReport {
        reports {
            html.isEnabled = true
            xml.isEnabled = true
            csv.isEnabled = true
        }
        finalizedBy("jacocoTestCoverageVerification")

        classDirectories.setFrom(
            fileTree(project.buildDir) {
                exclude(
                    "**/Q*.*",
                    "**/*Test.*"
                )
                include(
                    "**/classes/**/main/**"
                )
            }
        )
    }
}
