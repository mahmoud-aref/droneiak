package com.elmenus.infrastructure.aws.s3.model

import software.amazon.awssdk.services.s3.model.CompletedPart


class UploadStatus(val contentType: String, val fileKey: String) {

    var uploadId: String? = null
    var partCounter: Int = 0
    var buffered: Int = 0

    var completedParts: Map<Int, CompletedPart> =  mutableMapOf()

    fun addBuffered(buffered: Int) {
        this.buffered += buffered
    }

    val addedPartCounter: Int
        get() = ++this.partCounter
}