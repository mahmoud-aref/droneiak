package com.elmenus.presentation.rest.facade.impl

import com.elmenus.application.user.usecase.UserCrudUseCase
import com.elmenus.presentation.rest.facade.UserFacade
import com.elmenus.presentation.rest.model.UserRegistrationRequest
import com.elmenus.presentation.rest.model.UserRegistrationResponse
import com.elmenus.presentation.rest.model.UserTokenRequest
import com.elmenus.presentation.rest.model.UserTokenResponse
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserFacadeImpl(
    private val userCrudUseCase: UserCrudUseCase
) : UserFacade {

    override fun registerUser(userRegistrationRequest: UserRegistrationRequest): Mono<UserRegistrationResponse> {
        return userCrudUseCase
            .registerUser(userRegistrationRequest.toUserEntity())
            .map { UserRegistrationResponse.fromEntity(it) }
    }

    override fun authenticate(userTokenRequest: UserTokenRequest): Mono<UserTokenResponse> {
        return userCrudUseCase
            .authenticate(userTokenRequest.username, userTokenRequest.password)
            .map { UserTokenResponse(it) }
    }
}