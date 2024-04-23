package com.elmenus.infrastructure.security.jwt

import org.springframework.security.core.Authentication

interface JWTProvider {
    fun generateToken(auth: Authentication): String
    fun getTokenFromHeader(header: String): String
    fun getAuthentication(token: String): Authentication
    fun validateTokenAndReturnUsername(token: String): String
}