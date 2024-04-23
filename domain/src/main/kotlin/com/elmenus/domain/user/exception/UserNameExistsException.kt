package com.elmenus.domain.user.exception

import com.elmenus.domain.common.exception.DomainException
import com.elmenus.domain.user.tokens.UserDomainTokens

class UserNameExistsException : DomainException(UserDomainTokens.USERNAME_EXISTS)