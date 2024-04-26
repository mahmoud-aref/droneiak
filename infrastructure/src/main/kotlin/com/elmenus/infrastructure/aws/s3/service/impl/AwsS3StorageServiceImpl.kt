package com.elmenus.infrastructure.aws.s3.service.impl

import com.elmenus.infrastructure.aws.config.AwsProperties
import com.elmenus.infrastructure.aws.s3.model.AwsS3Object
import com.elmenus.infrastructure.aws.s3.model.S3FileResponse
import com.elmenus.infrastructure.aws.s3.model.UploadStatus
import com.elmenus.infrastructure.aws.s3.service.AwsS3StorageService
import com.elmenus.infrastructure.aws.utils.FileUtils
import kotlinx.coroutines.reactive.collect
import org.springframework.http.MediaType
import org.springframework.http.codec.multipart.FilePart
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import software.amazon.awssdk.core.async.AsyncRequestBody
import software.amazon.awssdk.core.async.AsyncResponseTransformer
import software.amazon.awssdk.services.s3.S3AsyncClient
import software.amazon.awssdk.services.s3.model.*
import java.nio.ByteBuffer


@Service
class AwsS3StorageServiceImpl(
    private val awsS3Client: S3AsyncClient,
    private val awsProperties: AwsProperties
) : AwsS3StorageService {

    override fun uploadObject(filePart: FilePart): Mono<S3FileResponse> {

        val filename = filePart.filename()
        val metadata = mapOf(Pair("filename", filename))
        val mediaType = filePart.headers().contentType ?: MediaType.APPLICATION_OCTET_STREAM
        val s3AsyncClientMultipartUpload = awsS3Client.createMultipartUpload(
            CreateMultipartUploadRequest.builder()
                .contentType(mediaType.toString())
                .key(filename)
                .metadata(metadata)
                .bucket(awsProperties.s3BucketName)
                .build()
        )

        val uploadStatus = UploadStatus(mediaType.toString(), filename)

        return Mono.fromFuture(s3AsyncClientMultipartUpload)
            .flatMapMany { response ->
                FileUtils.checkSdkResponse(response)
                uploadStatus.uploadId = response.uploadId()
                filePart.content()
            }
            .bufferUntil {
                true
            }
            .map { FileUtils.dataBufferToByteBuffer(it) }
            .flatMap { uploadPartObject(uploadStatus, it) }
            .reduce(uploadStatus) { status, part ->
                status.addPart(part)
                status
            }
            .flatMap { completeMultipartUpload(it) }
            .map { response ->
                FileUtils.checkSdkResponse(response)
                S3FileResponse(
                    filename,
                    uploadStatus.uploadId.toString(),
                    response.location(),
                    uploadStatus.contentType,
                    response.eTag()
                )
            }
    }

    override fun getByteObject(key: String): Mono<ByteArray> {
        return Mono.just(GetObjectRequest.builder().bucket(awsProperties.s3BucketName).key(key).build())
            .map { awsS3Client.getObject(it, AsyncResponseTransformer.toBytes()) }
            .flatMap { Mono.fromFuture(it) }
            .map { it.asByteArray() }
    }

    override fun deleteObject(objectKey: String): Mono<Unit> {
        return Mono.just(DeleteObjectRequest.builder().bucket(awsProperties.s3BucketName).key(objectKey).build())
            .map { awsS3Client.deleteObject(it) }
            .map { Mono.fromFuture(it) }
            .thenReturn(Unit)
    }

    override fun getObjects(): Flux<AwsS3Object> {
        return Flux.from(awsS3Client.listObjectsV2Paginator(
            ListObjectsV2Request.builder().bucket(awsProperties.s3BucketName).build()
        ).flatMapIterable { it.contents() }
            .map { AwsS3Object(it.key(), it.lastModified(), it.eTag(), it.size()) })
    }

    private fun uploadPartObject(uploadStatus: UploadStatus, buffer: ByteBuffer): Mono<CompletedPart> {
        val partNumber = uploadStatus.addedPartCounter
        return Mono.fromFuture {
            awsS3Client.uploadPart(
                UploadPartRequest.builder()
                    .bucket(awsProperties.s3BucketName)
                    .key(uploadStatus.fileKey)
                    .partNumber(partNumber)
                    .uploadId(uploadStatus.uploadId)
                    .contentLength(buffer.capacity().toLong())
                    .build(),
                AsyncRequestBody.fromPublisher(Mono.just(buffer))
            )
        }.map {
            FileUtils.checkSdkResponse(it)
            uploadStatus.addPart(
                CompletedPart.builder().partNumber(partNumber).eTag(it.eTag()).build()
            )
        }
    }

    private fun completeMultipartUpload(uploadStatus: UploadStatus): Mono<CompleteMultipartUploadResponse> {
        val multipartUpload = CompletedMultipartUpload.builder()
            .parts(uploadStatus.completeParts.values)
            .build()
        return Mono.fromFuture(
            awsS3Client.completeMultipartUpload(
                CompleteMultipartUploadRequest.builder()
                    .bucket(awsProperties.s3BucketName)
                    .uploadId(uploadStatus.uploadId)
                    .multipartUpload(multipartUpload)
                    .key(uploadStatus.fileKey)
                    .build()
            )
        )
    }

}