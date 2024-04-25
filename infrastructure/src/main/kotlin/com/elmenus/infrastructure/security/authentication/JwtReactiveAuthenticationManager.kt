package com.elmenus.infrastructure.security.authentication

import com.elmenus.infrastructure.security.jwt.JwtProvider
import com.elmenus.infrastructure.security.service.JwtReactiveUserDetailsService
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class JwtReactiveAuthenticationManager(
    private val jwtProvider: JwtProvider,
    private val passwordEncoder: PasswordEncoder,
    userReactiveService: JwtReactiveUserDetailsService
) : UserDetailsRepositoryReactiveAuthenticationManager(
    userReactiveService
) {

    init {
        super.setPasswordEncoder(this.passwordEncoder)
    }

    override fun authenticate(authentication: Authentication): Mono<Authentication> {
        val token = authentication.credentials.toString()
        val username = if (jwtProvider.validateToken(token)) {
            jwtProvider.validateTokenAndReturnUsername(token)
        } else {
            authentication.principal.toString()
        }
        return this.retrieveUser(username)
            .map { details -> JwtAuthenticationToken(username, token, details.authorities) }
            .flatMap { Mono.just(it) }

    }

    override fun setPasswordEncoder(passwordEncoder: PasswordEncoder) {
        super.setPasswordEncoder(passwordEncoder)
    }

}