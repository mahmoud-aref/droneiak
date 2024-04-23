package com.elmenus.infrastructure.security.service

import com.elmenus.infrastructure.security.exceptions.UsernameNotFoundException
import com.elmenus.infrastructure.security.repository.UserReactiveRepository
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import reactor.core.publisher.Mono

class ReactiveUserDetailsService :
    ReactiveUserDetailsService {

    private lateinit var userReactiveRepository: UserReactiveRepository

    override fun findByUsername(username: String): Mono<UserDetails> {
        return Mono.just(
            userReactiveRepository.findByUsername(username)
                .orElseThrow { UsernameNotFoundException("Username Not Found") }
        )
    }

}