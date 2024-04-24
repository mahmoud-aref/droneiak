package com.elmenus.presentation.rest.controller

import com.elmenus.application.user.usecase.UserAuthUseCase
import com.elmenus.presentation.rest.model.UserTokenRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping(AuthController.AUTH_URL)
class AuthController(
    private val userAuthUseCase: UserAuthUseCase
) {

    companion object {
        const val AUTH_URL = "/api/v1/auth"
        const val TOKEN_URL = "/token"
    }

    @PostMapping(TOKEN_URL)
    fun getToken(@RequestBody userTokenRequest: UserTokenRequest): Mono<String> {
        return userAuthUseCase.authenticate(userTokenRequest.username, userTokenRequest.password)
    }

}