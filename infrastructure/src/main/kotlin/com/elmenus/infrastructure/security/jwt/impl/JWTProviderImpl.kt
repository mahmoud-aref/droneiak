package com.elmenus.infrastructure.security.jwt.impl

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import com.elmenus.infrastructure.security.exceptions.UnauthorizedException
import com.elmenus.infrastructure.security.jwt.JWTProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component

@Component
class JWTProviderImpl : JWTProvider {

    @Value("\${jwt.signing.key}")
    private val jwtSigningKey: String? = null

    override fun generateToken(auth: Authentication): String {
        val userName = auth.name
        val authorities = auth.authorities.joinToString { it.authority }
        val algorithm = Algorithm.HMAC256(jwtSigningKey)

        return JWT.create()
            .withSubject(userName)
            .withClaim("roles", authorities)
            .sign(algorithm)
    }

    override fun getTokenFromHeader(header: String): String {
        return header.replace("Bearer ", "")
    }

    override fun getAuthentication(token: String): Authentication {
        val claims = getPayload(token)
        val roles = claims.getClaim("roles").asString().split(",")
        val authorities = roles.map { role -> "ROLE_$role" }.map { SimpleGrantedAuthority(it) }
        val principal = User(claims.subject, "", authorities)
        return UsernamePasswordAuthenticationToken(principal, token, authorities)
    }

    override fun validateToken(token: String): Boolean {
        val algorithm = Algorithm.HMAC256(this.jwtSigningKey)
        val verifier = JWT.require(algorithm).build()
        try {
            verifier.verify(token)
            return true
        } catch (e: Exception) {
            return false
        }
    }

    override fun validateTokenAndReturnUsername(token: String): String {
        try {
            val claims = getPayload(token)
            return claims.subject
        } catch (e: Exception) {
            throw UnauthorizedException("Invalid token")

        }
    }

    private fun getPayload(token: String): DecodedJWT {
        val algorithm = Algorithm.HMAC256(this.jwtSigningKey)
        val verifier = JWT.require(algorithm).build()
        return verifier.verify(token)
    }
}