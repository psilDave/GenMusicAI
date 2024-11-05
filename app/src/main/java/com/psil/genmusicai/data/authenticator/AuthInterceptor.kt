package com.psil.genmusicai.data.authenticator

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val requestBuilder =
            original.newBuilder().header(
                "Authorization",
                "Bearer "
            )

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}