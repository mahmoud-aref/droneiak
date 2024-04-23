package com.elmenus.domain.product.repository

import com.elmenus.domain.product.model.Product
import java.util.Optional
import java.util.UUID
import java.util.concurrent.CompletableFuture

interface ProductRepository {
    fun getProducts(): CompletableFuture<Optional<List<Product>>>
    fun getProduct(id: UUID): CompletableFuture<Product>
    fun addProduct(product: Product): CompletableFuture<Product>
    fun updateProduct(product: Product): CompletableFuture<Product>
    fun deleteProduct(id: Int): CompletableFuture<Boolean>
}