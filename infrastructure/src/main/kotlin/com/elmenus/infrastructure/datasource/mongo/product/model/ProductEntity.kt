package com.elmenus.infrastructure.datasource.mongo.product.model

import com.elmenus.domain.product.model.Product
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document(collection = "products")
data class ProductEntity(
    val product: Product
) {
    @Id
    val id: UUID = product.id
}