package com.elmenus.domain.product.service

import com.elmenus.domain.product.model.Product
import java.util.UUID
import java.util.concurrent.CompletableFuture

interface ProductService {
    fun createProduct(product: Product): CompletableFuture<Product>
    fun getProduct(id: UUID): CompletableFuture<Product>
    fun updateProduct(product: Product): CompletableFuture<Product>
    fun deleteProduct(id: UUID): CompletableFuture<Boolean>
    fun getProducts(): CompletableFuture<List<Product>>
}