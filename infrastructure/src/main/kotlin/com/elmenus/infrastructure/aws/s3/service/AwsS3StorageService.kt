package com.elmenus.infrastructure.aws.s3.service

import com.elmenus.infrastructure.aws.s3.model.AwsS3Object
import com.elmenus.infrastructure.aws.s3.model.S3FileResponse
import org.springframework.http.codec.multipart.FilePart
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


interface AwsS3StorageService {
    fun uploadObject(filePart: FilePart): Mono<S3FileResponse>
    fun getByteObject(key: String): Mono<ByteArray>
    fun deleteObject(objectKey: String): Mono<Unit>
    fun getObjects(): Flux<AwsS3Object>
}