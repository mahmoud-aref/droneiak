package com.elmenus.application.user.config

import com.elmenus.application.user.repository.UserDao
import com.elmenus.domain.user.repository.UserRepository
import com.elmenus.domain.user.service.UserService
import com.elmenus.domain.user.service.impl.UserServiceImpl
import com.elmenus.infrastructure.security.repository.UserReactiveRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UserConfig(
    private val userReactiveRepository: UserReactiveRepository
) {

    @Bean
    fun userRepository(): UserRepository {
        return UserDao(userReactiveRepository)
    }

    @Bean
    fun userService(): UserService {
        return UserServiceImpl(userRepository())
    }
}