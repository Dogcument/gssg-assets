package com.gssg.assets.webapp

import com.gssg.assets.application.Application
import com.gssg.assets.persistence.Persistence
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * @Author Heli
 */
@SpringBootApplication(
    scanBasePackageClasses = [WebappApplication::class, Application::class, Persistence::class]
)
class WebappApplication

fun main(args: Array<String>) {
    runApplication<WebappApplication>(*args)
}
