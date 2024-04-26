package com.elmenus.domain.product.service.impl

import com.elmenus.domain.product.model.Product
import com.elmenus.domain.product.repository.ProductRepository
import com.elmenus.domain.product.service.ProductService
import java.util.*
import java.util.concurrent.CompletableFuture

class ProductServiceImpl(
    private val productRepository: ProductRepository
) : ProductService {

    override fun createProduct(product: Product): CompletableFuture<Product> {
        return productRepository.save(product)
    }

    override fun getProduct(id: UUID): CompletableFuture<Product> {
        return productRepository.findProductById(id)
            .thenApplyAsync { optionalProduct ->
                optionalProduct.orElseThrow { NoSuchElementException("Product not found") }
            }
    }

    override fun updateProduct(product: Product): CompletableFuture<Product> {
        return productRepository.updateProduct(product)
    }

    override fun deleteProduct(id: UUID): CompletableFuture<Boolean> {
        return productRepository.deleteProduct(id)
    }

    override fun getProducts(): CompletableFuture<List<Product>> {
        return productRepository.findAllProducts()
            .thenApplyAsync { optionalProducts ->
                optionalProducts.orElseGet { emptyList() }
            }
    }
}