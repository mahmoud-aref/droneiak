package com.elmenus.application.order.usecase

import com.elmenus.application.order.model.OrderCreation
import com.elmenus.application.order.model.OrderDto
import reactor.core.publisher.Mono
import java.util.*

interface OrderCycleUseCase {
    fun placeOrder(orderCreation: OrderCreation): Mono<OrderDto>
    fun getOrder(orderId: UUID): Mono<OrderDto>
    fun cancelOrder(orderId: UUID): Mono<OrderDto>
}