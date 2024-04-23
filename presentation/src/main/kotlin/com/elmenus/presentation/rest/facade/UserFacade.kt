package com.elmenus.presentation.rest.facade

import com.elmenus.presentation.rest.model.UserRegistrationResponse
import com.elmenus.presentation.rest.model.UserRegistrationRequest
import reactor.core.publisher.Mono

interface UserFacade {
    fun registerUser(userRegistrationRequest: UserRegistrationRequest): Mono<UserRegistrationResponse>
}