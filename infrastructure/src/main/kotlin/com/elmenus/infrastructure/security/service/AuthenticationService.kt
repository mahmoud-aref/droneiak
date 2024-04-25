package com.elmenus.infrastructure.security.service

import reactor.core.publisher.Mono

interface AuthenticationService {
    fun authenticate(username: String, password: String): Mono<String>
    fun validate(token: String): Mono<Boolean>
}