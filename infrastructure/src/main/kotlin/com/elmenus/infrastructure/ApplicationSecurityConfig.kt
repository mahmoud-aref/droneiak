package com.elmenus.infrastructure

import com.elmenus.infrastructure.security.authentication.JwtServerAuthenticationConverter
import com.elmenus.infrastructure.security.authentication.JwtReactiveAuthenticationManager
import com.elmenus.infrastructure.security.repository.JwtSecurityContextRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authentication.AuthenticationWebFilter
import org.springframework.security.web.server.savedrequest.NoOpServerRequestCache


@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity(useAuthorizationManager = true)
class ApplicationSecurityConfig(
    private val jwtSecurityContextRepository: JwtSecurityContextRepository,
    private val jwtServerAuthenticationConverter: JwtServerAuthenticationConverter,
    private val jwtReactiveAuthenticationManager: JwtReactiveAuthenticationManager,
) {

    @Bean
    fun filterChain(http: ServerHttpSecurity): SecurityWebFilterChain {

        val authenticationWebFilter = AuthenticationWebFilter(jwtReactiveAuthenticationManager)
        authenticationWebFilter.setServerAuthenticationConverter(jwtServerAuthenticationConverter)
        authenticationWebFilter.setSecurityContextRepository(jwtSecurityContextRepository)

        return http
            .addFilterAt(authenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
            .authorizeExchange { exchangeSpec ->
                exchangeSpec
                    .pathMatchers("/api/v1/auth/token").permitAll()
                    .pathMatchers("/api/v1/user/register").permitAll()
                    .pathMatchers("/api/v1/drones/**").hasAuthority("ADMIN")
                    .anyExchange().authenticated()
            }
            .cors { it.disable() }
            .csrf { it.disable() }
            .httpBasic { it.disable() }
            .formLogin { it.disable() }
            .requestCache { NoOpServerRequestCache.getInstance() }
            .build()
    }


}