package com.elmenus.presentation


import com.elmenus.infrastructure.ApplicationSecurityConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import
import org.springframework.web.reactive.config.EnableWebFlux

@EnableWebFlux
@SpringBootApplication(
    scanBasePackages = [
        "com.elmenus.infrastructure",
        "com.elmenus.application",
        "com.elmenus.presentation",
    ],
)
@Import(ApplicationSecurityConfig::class)
class PresentationApplication

fun main(args: Array<String>) {
    runApplication<PresentationApplication>(*args)
}


