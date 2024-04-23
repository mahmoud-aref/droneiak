package com.elmenus.domain.order.exception

import com.elmenus.domain.common.exception.DomainException
import com.elmenus.domain.order.tokens.OrderDomainToken

class InvalidOrderStateException : DomainException(OrderDomainToken.INVALID_STATE)