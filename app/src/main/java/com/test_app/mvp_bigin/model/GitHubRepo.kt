package com.test_app.mvp_bigin.model

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single


class GitHubRepo {
    private val users = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5"),
    )
    fun getUsers(): Single<List<GithubUser>> = Single.just(users)
}