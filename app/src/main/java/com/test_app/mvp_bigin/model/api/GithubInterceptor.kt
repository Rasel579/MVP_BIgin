package com.test_app.mvp_bigin.model.api

import com.test_app.mvp_bigin.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

object GithubInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        request.newBuilder()
            .header("Authorization", BuildConfig.GITHUB_DB_APIKEY)
            .build().apply {
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            }
        return chain.proceed(request)
    }
}