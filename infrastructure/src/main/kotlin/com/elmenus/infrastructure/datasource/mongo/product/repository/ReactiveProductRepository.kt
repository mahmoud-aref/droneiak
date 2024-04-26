package com.elmenus.infrastructure.datasource.mongo.product.repository

import com.elmenus.infrastructure.datasource.mongo.product.model.ProductEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import java.util.UUID

interface ReactiveProductRepository : ReactiveCrudRepository<ProductEntity, UUID> {
}