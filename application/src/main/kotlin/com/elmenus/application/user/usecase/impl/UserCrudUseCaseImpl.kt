package com.elmenus.application.user.usecase.impl

import com.elmenus.application.common.annotation.UseCase
import com.elmenus.application.user.repository.UserDao
import com.elmenus.application.user.usecase.UserCrudUseCase
import com.elmenus.infrastructure.datasource.user.UserEntity
import reactor.core.publisher.Mono

@UseCase
class UserCrudUseCaseImpl(
    private val userDao: UserDao
) : UserCrudUseCase {
    override fun registerUser(userEntity: UserEntity): Mono<UserEntity> {
        return Mono.fromFuture(
            userDao.save(userEntity.user)
        ).map { UserEntity(it) }
    }
}