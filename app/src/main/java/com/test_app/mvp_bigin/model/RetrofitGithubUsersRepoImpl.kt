package com.test_app.mvp_bigin.model

import com.test_app.mvp_bigin.model.api.ServiceApi
import com.test_app.mvp_bigin.utils.schedulers.Schedulers
import io.reactivex.rxjava3.core.Single

class RetrofitGithubUsersRepoImpl(
    private val api: ServiceApi,
    private val schedulers: Schedulers
) : GithubUsersRepo {
    override fun getUsers(): Single<List<GithubUser>> =
        api.getUsers()
            .subscribeOn(schedulers.background())


    override fun getRepos(url : String?): Single<List<GithubRepos>> =
        api.getRepos(url)
        .subscribeOn(schedulers.background())

    override fun getRepo(url: String?): Single<GithubRepos> = api
        .getRepo(url)
        .subscribeOn(schedulers.background())
}