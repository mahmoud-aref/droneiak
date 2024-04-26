package com.elmenus.application.product.model

import com.elmenus.domain.order.model.OrderItems
import com.elmenus.domain.product.model.Product
import java.util.*

data class ProductDto(
    val product: Product
) {
    fun toDomainProduct(): Product {
        return Product(
            id = product.id,
            name = product.name,
            price = product.price,
            description = product.description,
            imageUrl = product.imageUrl,
            rate = product.rate,
            weight = product.weight
        )
    }
}

data class ProductCreation(
    val name: String,
    val price: Double,
    val description: String,
    val imageUrl: String,
    val rate: Double,
    val weight: Double
) {
    fun toDomainProduct(): Product {
        return Product(
            id = UUID.randomUUID(),
            name = name,
            price = price,
            description = description,
            imageUrl = imageUrl,
            rate = rate,
            weight = weight
        )
    }
}


data class OrderItemsDto(
    val quantity: Int,
    val item: ProductDto
) {
    fun toDomainOrderItems(): OrderItems {
        return OrderItems(
            quantity = quantity,
            item = item.toDomainProduct()
        )
    }
}
