package com.elmenus.infrastructure.security.service.impl

import com.elmenus.infrastructure.security.authentication.ReactiveAuthenticationManager
import com.elmenus.infrastructure.security.jwt.JWTProvider
import com.elmenus.infrastructure.security.service.AuthenticationService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class AuthenticationServiceImpl(
    private val jwtProvider: JWTProvider,
    private val reactiveAuthenticationManager: ReactiveAuthenticationManager
) : AuthenticationService {

    override fun authenticate(username: String, password: String): Mono<String> {
        return this.reactiveAuthenticationManager
            .authenticate(UsernamePasswordAuthenticationToken(username, password, null))
            .map { jwtProvider.generateToken(it) }
    }
}