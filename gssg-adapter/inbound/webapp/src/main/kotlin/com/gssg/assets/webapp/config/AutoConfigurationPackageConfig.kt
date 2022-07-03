package com.gssg.assets.webapp.config

import com.gssg.assets.application.Application
import com.gssg.assets.persistence.Persistence
import org.springframework.boot.autoconfigure.AutoConfigurationPackage
import org.springframework.context.annotation.Configuration

/**
 * @Author Heli
 */
@Configuration
@AutoConfigurationPackage(basePackageClasses = [Application::class, Persistence::class])
class AutoConfigurationPackageConfig
