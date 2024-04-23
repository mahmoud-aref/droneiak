package com.elmenus.domain.order.exception

import com.elmenus.domain.common.exception.DomainException
import com.elmenus.domain.order.tokens.OrderDomainToken

class OrderNotFoundException : DomainException(OrderDomainToken.ORDER_NOT_FOUND)