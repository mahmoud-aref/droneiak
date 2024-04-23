package com.elmenus.infrastructure.security.authentication

import com.elmenus.infrastructure.security.jwt.JWTProvider
import com.elmenus.infrastructure.security.repository.UserReactiveRepository
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class ReactiveAuthenticationManager : ReactiveAuthenticationManager {

    lateinit var jwtProvider: JWTProvider
    lateinit var userReactiveRepository: UserReactiveRepository

    override fun authenticate(authentication: Authentication): Mono<Authentication> {
        val token = authentication.credentials.toString()
        val username = jwtProvider.validateTokenAndReturnUsername(token)
        return userReactiveRepository
            .findByUsername(username)
            .map { Mono.just(authentication) }
            .orElseGet { Mono.empty() }
    }
}