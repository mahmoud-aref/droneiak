package com.elmenus.infrastructure.security.authentication

import com.elmenus.infrastructure.security.jwt.JWTProvider
import com.elmenus.infrastructure.security.service.ReactiveUserDetailsService
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class ReactiveAuthenticationManager(
    private val jwtProvider: JWTProvider,
    private val userReactiveService: ReactiveUserDetailsService,
) : UserDetailsRepositoryReactiveAuthenticationManager(
    userReactiveService
) {

    override fun authenticate(authentication: Authentication): Mono<Authentication> {
        val token = authentication.credentials.toString()
        // check if token here is password or jwt token
        val username = if (jwtProvider.validateToken(token)) {
            jwtProvider.validateTokenAndReturnUsername(token)
        } else {
            authentication.principal.toString()
        }
        return this.retrieveUser(username)
            .map {
                Mono.just(UsernamePasswordAuthenticationToken(username, token, it.authorities))
            }
            .flatMap {
                it
            }
    }

    override fun setPasswordEncoder(passwordEncoder: PasswordEncoder) {
        super.setPasswordEncoder(passwordEncoder)
    }

}