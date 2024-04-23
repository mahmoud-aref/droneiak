package com.elmenus.application.user.usecase

import com.elmenus.infrastructure.datasource.user.UserEntity
import reactor.core.publisher.Mono

interface UserCrudUseCase {
    fun registerUser(userEntity: UserEntity): Mono<UserEntity>
}