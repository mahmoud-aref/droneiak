package com.elmenus.infrastructure.aws.utils

import software.amazon.awssdk.core.SdkResponse


class AwsSdkUtil {
    companion object {
        fun isErrorSdkHttpResponse(sdkResponse: SdkResponse): Boolean {
            return sdkResponse.sdkHttpResponse() == null || !sdkResponse.sdkHttpResponse().isSuccessful
        }
    }
}