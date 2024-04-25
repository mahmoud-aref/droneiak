package com.elmenus.infrastructure.security.authentication

import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class JWTServerAuthenticationConverter : ServerAuthenticationConverter {

    override fun convert(exchange: ServerWebExchange): Mono<Authentication> {
        return Mono.justOrEmpty(
            exchange.request.headers.getFirst(HttpHeaders.AUTHORIZATION)
        )
            .filter { it.startsWith("Bearer ") }
            .map { it.replace("Bearer ", "") }
            .map { jwt -> UsernamePasswordAuthenticationToken(jwt, jwt) }
    }
}