package com.elmenus.domain.order.model

import com.elmenus.domain.product.model.Product
import java.util.UUID

data class Order(
    val id: UUID,
    val userId: UUID,
    val items: List<OrderItems>,
    val state: OrderState = OrderState.PLACED,
    val totalWeight: Double,
    val totalPrice: Double
) {
    fun setState(state: OrderState): Order {
        return this.copy(state = state)
    }

    fun updateItems(items: List<OrderItems>): Order {
        return this.copy(items = items)
    }

}

data class OrderItems(
    val quantity: Int,
    val item: Product
)

enum class OrderState {
    PLACED,
    PREPARING,
    READY,
    DELIVERING,
    DELIVERED,
    CANCELLED,
    REJECTED
}