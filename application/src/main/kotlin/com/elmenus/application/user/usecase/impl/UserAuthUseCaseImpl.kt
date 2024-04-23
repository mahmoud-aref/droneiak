package com.elmenus.application.user.usecase.impl

import com.elmenus.application.common.annotation.UseCase
import com.elmenus.application.user.usecase.UserAuthUseCase
import com.elmenus.infrastructure.security.service.AuthenticationService
import reactor.core.publisher.Mono

@UseCase
class UserAuthUseCaseImpl(
    private val authenticationService: AuthenticationService
) : UserAuthUseCase {
    override fun authenticate(username: String, password: String): Mono<String> {
        return authenticationService.authenticate(username, password)
    }
}