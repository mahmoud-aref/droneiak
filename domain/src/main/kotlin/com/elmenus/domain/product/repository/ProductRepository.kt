package com.elmenus.domain.product.repository

import com.elmenus.domain.product.model.Product
import java.util.Optional
import java.util.UUID
import java.util.concurrent.CompletableFuture

interface ProductRepository {
    fun findAllProducts(): CompletableFuture<Optional<List<Product>>>
    fun findProductById(id: UUID): CompletableFuture<Optional<Product>>
    fun save(product: Product): CompletableFuture<Product>
    fun updateProduct(product: Product): CompletableFuture<Product>
    fun deleteProduct(id: UUID): CompletableFuture<Boolean>
}