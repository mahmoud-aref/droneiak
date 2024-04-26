package com.elmenus.infrastructure.aws.utils

import org.springframework.core.io.buffer.DataBuffer
import org.springframework.http.codec.multipart.FilePart
import org.springframework.util.ObjectUtils
import org.springframework.util.StringUtils
import software.amazon.awssdk.core.SdkResponse
import java.text.MessageFormat
import java.util.*
import java.nio.ByteBuffer;

class FileUtils {
    companion object {
        private val contentTypes = listOf(
            "image/png",
            "image/jpg",
            "image/jpeg",
            "image/bmp",
            "image/gif",
            "image/ief",
            "image/pipeg",
            "image/svg+xml",
            "image/tiff"
        )

        private fun isValidType(filePart: FilePart): Boolean {
            return isSupportedContentType(Objects.requireNonNull(filePart.headers().contentType).toString())
        }

        private fun isEmpty(filePart: FilePart): Boolean {
            return (StringUtils.hasText(filePart.filename()) && ObjectUtils.isEmpty(filePart.headers().contentType))
        }

        private fun isSupportedContentType(contentType: String): Boolean {
            return contentType.contains(contentType)
        }


        fun dataBufferToByteBuffer(buffers: List<DataBuffer>): ByteBuffer {
            var partSize = 0
            for (b in buffers) {
                partSize += b.readableByteCount()
            }
            val partData: ByteBuffer = ByteBuffer.allocate(partSize)
            buffers.forEach { b ->
                val bytes = ByteArray(b.readableByteCount())
                b.read(bytes)
                partData.put(bytes)
            }
            partData.rewind()
            return partData
        }

        fun checkSdkResponse(sdkResponse: SdkResponse) {
            if (AwsSdkUtil.isErrorSdkHttpResponse(sdkResponse)) {
                throw RuntimeException(
                    MessageFormat.format(
                        "{0} - {1}",
                        sdkResponse.sdkHttpResponse().statusCode(),
                        sdkResponse.sdkHttpResponse().statusText()
                    )
                )
            }
        }

        fun filePartValidator(filePart: FilePart) {
            if (isEmpty(filePart)) {
                throw RuntimeException("File cannot be empty or null!")
            }
            if (!isValidType(filePart)) {
                throw RuntimeException("Invalid file type")
            }
        }
    }
}