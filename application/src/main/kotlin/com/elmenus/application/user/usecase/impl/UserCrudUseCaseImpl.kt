package com.elmenus.application.user.usecase.impl

import com.elmenus.application.common.annotation.UseCase
import com.elmenus.application.user.repository.UserDao
import com.elmenus.application.user.usecase.UserCrudUseCase
import com.elmenus.domain.user.service.UserService
import com.elmenus.infrastructure.datasource.user.UserEntity
import com.elmenus.infrastructure.security.service.AuthenticationService
import reactor.core.publisher.Mono

@UseCase
class UserCrudUseCaseImpl(
    private val userService: UserService,
    private val authenticationService: AuthenticationService
) : UserCrudUseCase {

    override fun registerUser(userEntity: UserEntity): Mono<UserEntity> {
        return Mono.fromFuture(
            userService.createUser(userEntity.user)
        ).map { UserEntity(it) }
    }

    override fun authenticate(username: String, password: String): Mono<String> {
        return authenticationService.authenticate(username, password)
    }

}