package com.elmenus.application.product.model

import com.elmenus.domain.order.model.OrderItems
import com.elmenus.domain.product.model.Product


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
