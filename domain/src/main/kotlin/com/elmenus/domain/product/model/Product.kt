package com.elmenus.domain.product.model

import java.util.UUID

data class Product(
    val id: UUID,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val rate: Double
)
