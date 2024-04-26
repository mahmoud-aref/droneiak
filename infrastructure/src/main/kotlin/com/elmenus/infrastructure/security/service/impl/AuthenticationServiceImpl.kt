package com.elmenus.infrastructure.security.service.impl

import com.elmenus.infrastructure.security.authentication.JwtAuthenticationToken
import com.elmenus.infrastructure.security.authentication.JwtReactiveAuthenticationManager
import com.elmenus.infrastructure.security.jwt.JwtProvider
import com.elmenus.infrastructure.security.service.AuthenticationService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class AuthenticationServiceImpl(
    private val jwtProvider: JwtProvider,
    private val jwtReactiveAuthenticationManager: JwtReactiveAuthenticationManager
) : AuthenticationService {

    override fun authenticate(username: String, password: String): Mono<String> {
        return this.jwtReactiveAuthenticationManager
            .authenticate(JwtAuthenticationToken(username, password, emptySet()))
            .map { jwtProvider.generateToken(it) }
    }

    override fun validate(token: String): Mono<Boolean> {
        return Mono.just(jwtProvider.validateToken(token))
    }
}