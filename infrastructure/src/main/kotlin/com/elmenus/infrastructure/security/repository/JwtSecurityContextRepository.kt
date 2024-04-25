package com.elmenus.infrastructure.security.repository

import com.elmenus.infrastructure.security.authentication.JwtReactiveAuthenticationManager
import com.elmenus.infrastructure.security.jwt.JwtProvider
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.security.web.server.context.ServerSecurityContextRepository
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class JwtSecurityContextRepository(
    private val jwtProvider: JwtProvider
) : ServerSecurityContextRepository {

    override fun save(exchange: ServerWebExchange, context: SecurityContext): Mono<Void> {
        return Mono.empty()  // no session saving
    }

    override fun load(exchange: ServerWebExchange): Mono<SecurityContext> {
        return Mono.justOrEmpty(exchange.request.headers.getFirst(HttpHeaders.AUTHORIZATION))
            .filter { it.startsWith("Bearer ") }
            .map { it.replace("Barer ,", "") }
            .flatMap { Mono.just(jwtProvider.getAuthentication(it)).map { SecurityContextImpl() } }
            .map { it }
    }
}