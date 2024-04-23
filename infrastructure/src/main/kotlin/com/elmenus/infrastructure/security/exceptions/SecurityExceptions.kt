package com.elmenus.infrastructure.security.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Unauthorized Token")
class UnauthorizedException(message: String) : RuntimeException(message)

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Username Not Found")
class UsernameNotFoundException(message: String) : RuntimeException(message)
