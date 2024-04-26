package com.elmenus.infrastructure.aws.s3.model

import software.amazon.awssdk.services.s3.model.CompletedPart
import software.amazon.awssdk.services.s3.model.UploadPartResponse


class UploadStatus(val contentType: String, val fileKey: String) {

    var uploadId: String? = null
    private var partCounter: Int = 0
    private var buffered: Int = 0

    private var completedParts: Map<Int, CompletedPart> = emptyMap()

    val completeParts: Map<Int, CompletedPart>
        get() = this.completedParts

    fun addPart(part: CompletedPart): CompletedPart {
        this.completedParts = this.completedParts.plus(Pair(part.partNumber(), part))
        return part
    }
    
    fun addBuffered(buffered: Int) {
        this.buffered += buffered
    }

    val addedPartCounter: Int
        get() = ++this.partCounter
}