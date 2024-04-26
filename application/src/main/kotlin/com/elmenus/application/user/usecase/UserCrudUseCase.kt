package com.elmenus.application.user.usecase

import com.elmenus.infrastructure.datasource.mongo.user.model.UserEntity
import reactor.core.publisher.Mono

interface UserCrudUseCase {
    fun registerUser(userEntity: UserEntity): Mono<UserEntity>
    fun authenticate(username: String, password: String): Mono<String>
}