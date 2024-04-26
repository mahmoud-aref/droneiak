package com.elmenus.application.product.usecase

import com.elmenus.application.product.model.ProductCreation
import com.elmenus.application.product.model.ProductDto
import org.springframework.http.codec.multipart.FilePart
import reactor.core.publisher.Mono

interface ProductCrudUseCase {
    fun createProduct(productCreation: ProductCreation, imageFilePart: Mono<FilePart>): Mono<ProductDto>
    fun updateProduct(productDto: ProductDto): Mono<ProductDto>
}