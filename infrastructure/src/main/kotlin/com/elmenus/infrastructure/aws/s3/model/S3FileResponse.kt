package com.elmenus.infrastructure.aws.s3.model

data class S3FileResponse(
    val name: String,
    val uploadId: String,
    val path: String,
    val type: String,
    val eTag: String
)