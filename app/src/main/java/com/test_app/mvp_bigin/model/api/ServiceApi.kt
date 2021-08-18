package com.test_app.mvp_bigin.model.api

import com.test_app.mvp_bigin.model.retrofit.GithubRepos
import com.test_app.mvp_bigin.model.retrofit.GithubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface ServiceApi {
    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>

    @GET
    fun getRepos(@Url url: String?): Single<List<GithubRepos>>

    @GET
    fun getRepo(@Url url: String?): Single<GithubRepos>
}