package com.elmenus.presentation.rest.controller

import com.elmenus.presentation.rest.facade.UserFacade
import com.elmenus.presentation.rest.model.UserTokenRequest
import com.elmenus.presentation.rest.model.UserTokenResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping(AuthController.AUTH_URL)
class AuthController(
    private val userFacade: UserFacade
) {

    companion object {
        const val AUTH_URL = "/api/v1/auth"
        const val TOKEN_URL = "/token"
    }

    @PostMapping(TOKEN_URL)
    fun getToken(@RequestBody userTokenRequest: UserTokenRequest): Mono<UserTokenResponse> {
        return userFacade.authenticate(userTokenRequest)
    }

}