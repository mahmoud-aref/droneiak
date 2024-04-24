package com.elmenus.infrastructure.security.service

import com.elmenus.infrastructure.datasource.user.UserDetailsImpl
import com.elmenus.infrastructure.security.exceptions.UsernameNotFoundException
import com.elmenus.infrastructure.security.repository.UserReactiveRepository
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ReactiveUserDetailsService(
    private val userReactiveRepository: UserReactiveRepository
) : ReactiveUserDetailsService {

    override fun findByUsername(username: String): Mono<UserDetails> {
        return userReactiveRepository.findByUserUsername(username)
            .map { UserDetailsImpl(it) }
    }

}