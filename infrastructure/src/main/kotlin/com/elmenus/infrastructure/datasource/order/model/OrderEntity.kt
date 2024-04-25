package com.elmenus.infrastructure.datasource.order.model

import com.elmenus.domain.order.model.Order
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document(collection = "orders")
data class OrderEntity(
    val order: Order
) {
    @Id
    val id: UUID = order.id
    fun Order.toEntity(): OrderEntity = OrderEntity(this)
}