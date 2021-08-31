package com.test_app.mvp_bigin.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.test_app.mvp_bigin.model.api.GithubInterceptor
import com.test_app.mvp_bigin.model.api.ServiceApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class GithubApiModule {
    @Provides
    fun provideBaseUrl(): String = "https://api.github.com"

    @Provides
    fun provideGithubApi(baseUrl: String): ServiceApi = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(OkHttpClient()
            .newBuilder().apply {
                connectTimeout(20, TimeUnit.SECONDS)
                readTimeout(20, TimeUnit.SECONDS)
                writeTimeout(20, TimeUnit.SECONDS)
            }
            .addInterceptor(GithubInterceptor)
            .build()
        )
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(ServiceApi::class.java)

    private val gson: Gson = GsonBuilder()
        .create()

}