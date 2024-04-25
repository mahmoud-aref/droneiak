package com.elmenus.application.order.usecase.impl

import com.elmenus.application.common.annotation.UseCase
import com.elmenus.application.order.model.OrderCreation
import com.elmenus.application.order.model.OrderDto
import com.elmenus.application.order.usecase.OrderCrudUseCase
import com.elmenus.domain.drone.service.DroneService
import com.elmenus.domain.order.service.OrderService
import reactor.core.publisher.Mono
import java.util.UUID

@UseCase
class OrderCrudUseCaseImpl(
    private val orderService: OrderService,
    private val droneService: DroneService
) : OrderCrudUseCase {

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

    override fun assignDroneToOrder(orderId: UUID, droneId: UUID): Mono<OrderDto> {
        TODO(
            "handle here the load of drone - do we need to assign order to drone or drone to order !?"
        )
    }


}