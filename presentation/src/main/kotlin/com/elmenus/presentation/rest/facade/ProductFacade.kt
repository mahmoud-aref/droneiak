package com.elmenus.presentation.rest.facade

import com.elmenus.presentation.rest.model.ProductCreationRequest
import com.elmenus.presentation.rest.model.ProductResponse
import org.springframework.http.codec.multipart.FilePart
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ProductFacade {
    fun createProduct(
        imageFilePart: Mono<FilePart>,
        productCreationRequest: Mono<ProductCreationRequest>
    ): Mono<ProductResponse>
    fun getProduct(productId: Long): Mono<ProductResponse>
    fun getProducts(): Flux<ProductResponse>
    fun updateProduct(productId: Long, productCreationRequest: ProductCreationRequest): Mono<ProductResponse>
    fun deleteProduct(productId: Long)
}