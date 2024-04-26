package com.elmenus.infrastructure.aws.s3.model

import java.time.Instant


data class AwsS3Object(
    val key: String,
    val lastModified: Instant,
    val eTag: String,
    val size: Long
)