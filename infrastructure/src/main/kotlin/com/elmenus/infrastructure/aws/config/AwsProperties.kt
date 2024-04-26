package com.elmenus.infrastructure.aws.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "aws")
class AwsProperties {
    lateinit var accessKey: String
    lateinit var secretKey: String
    lateinit var region: String
    lateinit var s3BucketName: String
    lateinit var multipartMinPartSize: Integer
    lateinit var awslocalUrl: String
}