package com.elmenus.application.user.config

import com.elmenus.application.user.repository.UserDao
import com.elmenus.domain.user.service.UserService
import com.elmenus.domain.user.service.impl.UserServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UserConfig(
    private val userDao: UserDao,
) {
    @Bean
    fun userService(): UserService {
        return UserServiceImpl(userDao)
    }
}