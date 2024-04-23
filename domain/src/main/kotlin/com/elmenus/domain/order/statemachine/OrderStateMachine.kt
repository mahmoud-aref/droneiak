package com.elmenus.domain.order.statemachine

import com.elmenus.domain.order.exception.InvalidOrderStateException
import com.elmenus.domain.order.model.Order
import com.elmenus.domain.order.model.OrderState

class OrderStateMachine {

    fun next(order: Order): Order {
        return when (order.state) {
            OrderState.PLACED -> order.setState(OrderState.PREPARING)
            OrderState.PREPARING -> order.setState(OrderState.READY)
            OrderState.READY -> order.setState(OrderState.DELIVERING)
            OrderState.DELIVERING -> order.setState(OrderState.DELIVERED)
            else -> throw InvalidOrderStateException()
        }
    }

    fun cancel(order: Order): Order {
       if (order.state == OrderState.DELIVERED) {
            throw InvalidOrderStateException()
        }
        return order.setState(OrderState.CANCELLED)
    }

    fun reject(order: Order): Order {
        if (order.state == OrderState.DELIVERED) {
            throw InvalidOrderStateException()
        }
        return order.setState(OrderState.REJECTED)
    }
}