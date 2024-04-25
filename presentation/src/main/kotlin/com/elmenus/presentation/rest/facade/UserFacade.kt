package com.elmenus.presentation.rest.facade

import com.elmenus.presentation.rest.model.UserRegistrationResponse
import com.elmenus.presentation.rest.model.UserRegistrationRequest
import com.elmenus.presentation.rest.model.UserTokenRequest
import com.elmenus.presentation.rest.model.UserTokenResponse
import reactor.core.publisher.Mono

interface UserFacade {
    fun registerUser(userRegistrationRequest: UserRegistrationRequest): Mono<UserRegistrationResponse>
    fun authenticate(userTokenRequest: UserTokenRequest): Mono<UserTokenResponse>
}