package com.elmenus.infrastructure

import com.elmenus.infrastructure.security.authentication.JWTServerAuthenticationConverter
import com.elmenus.infrastructure.security.authentication.ReactiveAuthenticationManager
import com.elmenus.infrastructure.security.repository.SecurityContextRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
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
    private val securityContextRepository: SecurityContextRepository,
    private val authenticationConverter: JWTServerAuthenticationConverter,
    private val reactiveAuthenticationManager: ReactiveAuthenticationManager,
) {

    @Bean
    fun filterChain(http: ServerHttpSecurity): SecurityWebFilterChain {

        val authenticationWebFilter = AuthenticationWebFilter(reactiveAuthenticationManager)
        authenticationWebFilter.setServerAuthenticationConverter(authenticationConverter)
        authenticationWebFilter.setSecurityContextRepository(securityContextRepository)

        return http
            .authorizeExchange { exchangeSpec ->
                exchangeSpec
                    .pathMatchers("/api/v1/auth/token").permitAll()
                    .pathMatchers("/api/v1/user/register").permitAll()
                    .pathMatchers(HttpMethod.POST, "/api/v1/drones/**").hasAuthority("ADMIN")
                    .pathMatchers(HttpMethod.PUT, "/api/v1/drones/**").hasAuthority("ADMIN")
                    .pathMatchers(HttpMethod.DELETE, "/api/v1/drones/**").hasAuthority("ADMIN")
                    .pathMatchers(HttpMethod.GET, "/api/v1/drones/**").hasAnyAuthority("USER", "ADMIN")
                    .anyExchange().authenticated()
            }
            .cors { it.disable() }
            .csrf { it.disable() }
            .httpBasic { it.disable() }
            .formLogin { it.disable() }
            .addFilterAt(authenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
            .requestCache { NoOpServerRequestCache.getInstance() }
            .build()
    }


}