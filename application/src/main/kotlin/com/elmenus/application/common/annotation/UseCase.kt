package com.elmenus.application.common.annotation

import org.springframework.stereotype.Service


@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Service
annotation class UseCase