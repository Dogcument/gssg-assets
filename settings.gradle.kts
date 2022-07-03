pluginManagement {
    val kotlinVersion: String by settings
    val springBootVersion: String by settings
    val springDependencyManagementVersion: String by settings

    plugins {
        kotlin("jvm") version kotlinVersion
        kotlin("plugin.spring") version kotlinVersion
        id("org.springframework.boot") version springBootVersion
        id("io.spring.dependency-management") version springDependencyManagementVersion
    }
}

rootProject.name = "gssg-assets"

include("gssg-interface")
include("gssg-application")
include("gssg-domain")
include("gssg-adapter:outbound:persistence")
include("gssg-adapter:inbound:webapp")
