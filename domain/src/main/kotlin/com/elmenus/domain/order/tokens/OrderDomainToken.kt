package com.elmenus.domain.order.tokens

interface OrderDomainToken {
    companion object {
        const val ORDER_NOT_FOUND = "order-not-found"
        const val INVALID_STATE = "invalid-state"
    }
}