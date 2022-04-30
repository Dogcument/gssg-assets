plugins {
    id("com.ewerk.gradle.plugins.querydsl")
}

dependencies {
    implementation("com.querydsl:querydsl-jpa")
}

val querydslDir = "$buildDir/generated/querydsl"

querydsl {
    jpa = true
    querydslSourcesDir = querydslDir
}

sourceSets {
    main.get().java.srcDirs(querydslDir)
}

configurations {
    querydsl.get().extendsFrom(compileClasspath.get())
}

tasks.compileQuerydsl {
    options.annotationProcessorPath = configurations.querydsl.get()
}
