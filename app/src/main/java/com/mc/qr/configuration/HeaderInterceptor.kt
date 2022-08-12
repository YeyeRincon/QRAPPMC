package com.mc.qr.configuration

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().addHeader(
            "Authorization:", "Content-Type:application/json"
        )
            .build()

        return chain.proceed(request)
    }


}