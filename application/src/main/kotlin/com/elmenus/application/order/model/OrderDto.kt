package com.elmenus.application.order.model

import com.elmenus.application.product.model.OrderItemsDto
import com.elmenus.domain.order.model.Order
import com.elmenus.domain.order.model.OrderState
import java.util.UUID

data class OrderDto(
    val order: Order
)

data class OrderCreation(
    val userId: UUID,
    val items: List<OrderItemsDto>
) {
    fun toDomainOrder(): Order {
        return Order(
            id = UUID.randomUUID(),
            userId = userId,
            items = items.stream().map { it.toDomainOrderItems() }.toList(),
            state = OrderState.PLACED,
            totalPrice = items.stream().map { it.item.product.price * it.quantity }.reduce { a, b -> a + b }.get(),
            totalWeight = items.stream().map { it.item.product.weight * it.quantity }.reduce { a, b -> a + b }.get()
        )
    }
}

data class DroneAssignmentToOrder(
    val orderId: String,
    val droneId: String
)