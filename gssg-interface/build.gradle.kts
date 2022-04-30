plugins {
    id("com.ewerk.gradle.plugins.querydsl")
}

dependencies {
    implementation("com.querydsl:querydsl-jpa:5.0.0")
    implementation("com.querydsl:querydsl-apt:5.0.0")
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
    compileOnly {
        extendsFrom(annotationProcessor.get())
    }
    querydsl.get().extendsFrom(compileClasspath.get())
}

tasks.compileQuerydsl {
    options.annotationProcessorPath = configurations.querydsl.get()
}
