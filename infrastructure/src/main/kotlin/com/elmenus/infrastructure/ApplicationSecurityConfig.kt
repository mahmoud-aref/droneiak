package com.elmenus.infrastructure

import com.elmenus.infrastructure.security.authentication.ReactiveAuthenticationManager
import com.elmenus.infrastructure.security.repository.SecurityContextRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.savedrequest.NoOpServerRequestCache

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class ApplicationSecurityConfig(
    private val reactiveAuthenticationManager: ReactiveAuthenticationManager,
    private val securityContextRepository: SecurityContextRepository
) {

    @Bean
    fun filterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http
            .authorizeExchange {
                it.pathMatchers("/api/v1/auth/token").permitAll()
                it.pathMatchers("/api/v1/user/register").permitAll()
                it.anyExchange().authenticated()
            }
            .cors { it.disable() }
            .csrf { it.disable() }
            .httpBasic { it.disable() }
            .formLogin { it.disable() }
            .authenticationManager(reactiveAuthenticationManager)
            .securityContextRepository(securityContextRepository)
            .requestCache { NoOpServerRequestCache.getInstance() }
            .build()
    }

}