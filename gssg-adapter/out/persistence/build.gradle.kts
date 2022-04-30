dependencies {
    api(project(":gssg-domain"))
    api(project(":gssg-application"))

    implementation("org.jetbrains.exposed:exposed-spring-boot-starter:0.38.2")
    implementation("org.jetbrains.exposed:exposed-core:0.38.2")
    implementation("org.jetbrains.exposed:exposed-java-time:0.38.2")
}
