package com.elmenus.presentation.rest.controller

import com.elmenus.presentation.rest.facade.UserFacade
import com.elmenus.presentation.rest.model.UserRegistrationResponse
import com.elmenus.presentation.rest.model.UserRegistrationRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping(UserController.USER_URL)
class UserController(
    private val userFacade: UserFacade
) {
    companion object {
        const val USER_URL = "/api/v1/user"
        const val USER_REGISTER_URL = "/register"
    }

    @PostMapping(USER_REGISTER_URL)
    fun registerUser(@RequestBody userRegistrationRequest: UserRegistrationRequest): Mono<UserRegistrationResponse> {
        return userFacade.registerUser(userRegistrationRequest)
    }

}