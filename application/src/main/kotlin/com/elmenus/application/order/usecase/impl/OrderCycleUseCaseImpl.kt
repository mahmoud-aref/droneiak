package com.elmenus.application.order.usecase.impl

import com.elmenus.application.common.annotation.UseCase
import com.elmenus.application.order.model.OrderCreation
import com.elmenus.application.order.model.OrderDto
import com.elmenus.application.order.usecase.OrderCycleUseCase
import com.elmenus.domain.order.service.OrderService
import reactor.core.publisher.Mono
import java.util.UUID

@UseCase
class OrderCycleUseCaseImpl(
    private val orderService: OrderService,
) : OrderCycleUseCase {

    override fun placeOrder(orderCreation: OrderCreation): Mono<OrderDto> {
        return Mono.fromFuture(orderService.placeOrder(orderCreation.toDomainOrder()))
            .map { OrderDto(it) }

    }

    override fun getOrder(orderId: UUID): Mono<OrderDto> {
        return Mono.fromFuture(orderService.getOrder(orderId))
            .map { OrderDto(it) }
    }

    override fun cancelOrder(orderId: UUID): Mono<OrderDto> {
        return Mono.fromFuture(orderService.cancelOrder(orderId))
            .map { OrderDto(it) }
    }

}