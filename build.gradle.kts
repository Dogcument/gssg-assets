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

    dependencies {
        implementation(kotlin("stdlib-jdk8"))
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

        implementation("org.springframework.boot:spring-boot-starter-actuator")
        implementation("org.springframework.boot:spring-boot-starter-security")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-validation")
        implementation("org.springframework.boot:spring-boot-starter-webflux")

        implementation("org.jetbrains.exposed:exposed-spring-boot-starter:0.38.2")
        
        testImplementation("org.springframework.boot:spring-boot-starter-test") {
            exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        }
        testImplementation("org.springframework.security:spring-security-test")
    }

    val jar: Jar by tasks
    val bootJar: BootJar by tasks

    bootJar.enabled = false
    jar.enabled = true

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.test {
        extensions.configure(JacocoTaskExtension::class) {
            setDestinationFile(file("$buildDir/jacoco/jacoco.exec"))
        }

        finalizedBy("jacocoTestReport")
    }

    jacoco {
        toolVersion = "0.8.7"
    }

    tasks.jacocoTestReport {
        reports {
            xml.isEnabled = true
            html.isEnabled = true
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
