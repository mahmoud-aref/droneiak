package com.elmenus.presentation.rest.controller

import com.elmenus.presentation.rest.facade.ProductFacade
import com.elmenus.presentation.rest.model.ProductCreationRequest
import com.elmenus.presentation.rest.model.ProductResponse
import org.springframework.http.MediaType
import org.springframework.http.codec.multipart.FilePart
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping(ProductController.PRODUCT_PATH)
class ProductController(
    private val productFacade: ProductFacade
) {

    companion object {
        const val PRODUCT_PATH = "/api/v1/products"
        const val PRODUCT_CREATE_PATH = "/create"
    }

    @PostMapping(
        PRODUCT_CREATE_PATH,
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun createProduct(
        @RequestPart("image") imageFilePart: Mono<FilePart>,
        @RequestPart("body") productCreationRequest: Mono<ProductCreationRequest>
    ): Mono<ProductResponse> {
        return productFacade.createProduct(imageFilePart, productCreationRequest)
    }

}