package com.elmenus.application.product.repository

import com.elmenus.domain.product.model.Product
import com.elmenus.domain.product.repository.ProductRepository
import com.elmenus.infrastructure.datasource.mongo.product.model.ProductEntity
import com.elmenus.infrastructure.datasource.mongo.product.repository.ReactiveProductRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.util.*
import java.util.concurrent.CompletableFuture

@Repository
class ProductDao(
    private val reactiveProductRepository: ReactiveProductRepository
) : ProductRepository {

    override fun findAllProducts(): CompletableFuture<Optional<List<Product>>> {
        return reactiveProductRepository.findAll()
            .map { it.product }
            .collectList()
            .map { Optional.of(it) }
            .defaultIfEmpty(Optional.empty())
            .toFuture()
    }

    override fun findProductById(id: UUID): CompletableFuture<Optional<Product>> {
        return reactiveProductRepository.findById(id)
            .map { Optional.of(it.product) }
            .defaultIfEmpty(Optional.empty())
            .toFuture()
    }

    override fun save(product: Product): CompletableFuture<Product> {
        return reactiveProductRepository.save(ProductEntity(product))
            .map { it.product }
            .toFuture()
    }

    override fun updateProduct(product: Product): CompletableFuture<Product> {
        return reactiveProductRepository.save(ProductEntity(product))
            .map { it.product }
            .toFuture()
    }

    override fun deleteProduct(id: UUID): CompletableFuture<Boolean> {
        return reactiveProductRepository.deleteById(id)
            .then(Mono.fromCallable { true })
            .toFuture()
    }


}