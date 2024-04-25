package com.elmenus.infrastructure.security.service

import com.elmenus.infrastructure.datasource.user.UserDetailsImpl
import com.elmenus.infrastructure.datasource.user.repository.ReactiveUserRepository
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class JwtReactiveUserDetailsService(
    private val reactiveUserRepository: ReactiveUserRepository
) : ReactiveUserDetailsService {

    override fun findByUsername(username: String): Mono<UserDetails> {
        return reactiveUserRepository.findByUserUsername(username)
            .map { UserDetailsImpl(it) }
    }

}