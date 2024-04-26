package com.elmenus.application.product.usecase.impl

import com.elmenus.application.common.annotation.UseCase
import com.elmenus.application.product.model.ProductCreation
import com.elmenus.application.product.model.ProductDto
import com.elmenus.application.product.repository.ProductDao
import com.elmenus.application.product.usecase.ProductCrudUseCase
import com.elmenus.infrastructure.aws.s3.service.AwsS3StorageService
import org.springframework.http.codec.multipart.FilePart
import reactor.core.publisher.Mono

@UseCase
class ProductCrudUseCaseImpl(
    private val productDao: ProductDao,
    private val awsS3StorageService: AwsS3StorageService
) : ProductCrudUseCase {

    override fun createProduct(productCreation: ProductCreation, imageFilePart: Mono<FilePart>): Mono<ProductDto> {
        return imageFilePart
            .map { awsS3StorageService.uploadObject(it) }
            .flatMap { it }
            .map { productDao.save(productCreation.copy(imageUrl = it.path).toDomainProduct()) }
            .flatMap { Mono.fromFuture(it) }
            .map { ProductDto(it) }
    }

    override fun updateProduct(productDto: ProductDto): Mono<ProductDto> {
        TODO("Not yet implemented")
    }
}