package com.elmenus.presentation.rest.facade.impl

import com.elmenus.application.product.usecase.ProductCrudUseCase
import com.elmenus.presentation.rest.facade.ProductFacade
import com.elmenus.presentation.rest.model.ProductCreationRequest
import com.elmenus.presentation.rest.model.ProductResponse
import org.springframework.http.codec.multipart.FilePart
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ProductFacadeImpl(
    private val productCrudUseCase: ProductCrudUseCase
) : ProductFacade {

    override fun createProduct(
        imageFilePart: Mono<FilePart>,
        productCreationRequest: Mono<ProductCreationRequest>
    ): Mono<ProductResponse> {
        return productCreationRequest
            .map { it.productCreation }
            .flatMap { productCrudUseCase.createProduct(it, imageFilePart) }
            .map { ProductResponse(it) }
    }

    override fun getProduct(productId: Long): Mono<ProductResponse> {
        TODO("Not yet implemented")
    }

    override fun getProducts(): Flux<ProductResponse> {
        TODO("Not yet implemented")
    }

    override fun updateProduct(productId: Long, productCreationRequest: ProductCreationRequest): Mono<ProductResponse> {
        TODO("Not yet implemented")
    }

    override fun deleteProduct(productId: Long) {
        TODO("Not yet implemented")
    }


}