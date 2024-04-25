package com.elmenus.infrastructure.security.repository

import com.elmenus.domain.user.model.UserRole
import com.elmenus.infrastructure.security.authentication.ReactiveAuthenticationManager
import com.elmenus.infrastructure.security.jwt.JWTProvider
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.security.web.server.context.ServerSecurityContextRepository
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.util.Collections

@Component
class SecurityContextRepository(
    private val authenticationManager: ReactiveAuthenticationManager,
    private val jwtProvider: JWTProvider
) : ServerSecurityContextRepository {


    override fun save(exchange: ServerWebExchange?, context: SecurityContext?): Mono<Void> {
        return Mono.empty()
    }

    override fun load(exchange: ServerWebExchange): Mono<SecurityContext> {
        return Mono.justOrEmpty(exchange.request.headers.getFirst(HttpHeaders.AUTHORIZATION))
            .filter { it.startsWith("Bearer ") }
            .map { it.replace("Barer ,", "") }
            .flatMap {
                Mono.just(jwtProvider.getAuthentication(it)).map { SecurityContextImpl() }
            }
            .log()
            .map { it }

    }
}