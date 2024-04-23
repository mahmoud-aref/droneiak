package com.elmenus.application.user.usecase

import reactor.core.publisher.Mono

interface UserAuthUseCase {
    fun authenticate(username: String, password: String): Mono<String>
}