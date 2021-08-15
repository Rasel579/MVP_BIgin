package com.test_app.mvp_bigin.model.api

import okhttp3.Interceptor
import okhttp3.Response

object GithubInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        request.newBuilder()
            .header("Authorization", "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")
            .build()
        return chain.proceed(request)
    }
}