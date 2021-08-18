package com.test_app.mvp_bigin.model

import com.test_app.mvp_bigin.model.network.NetworkStatus
import com.test_app.mvp_bigin.model.retrofit.CloudSource
import com.test_app.mvp_bigin.model.retrofit.GithubRepos
import com.test_app.mvp_bigin.model.retrofit.GithubUser
import com.test_app.mvp_bigin.model.storage.Storage
import com.test_app.mvp_bigin.utils.schedulers.Schedulers
import io.reactivex.rxjava3.core.Single

class RepositoryImpl(
    private val cloud: CloudSource,
    private val storage: Storage,
    private val network: NetworkStatus,
    private val schedulers: Schedulers
) : GithubUsersRepo {
    override fun getUsers(): Single<List<GithubUser>> = network
        .onLineSingle()
        .flatMap { isOnline ->
            if (isOnline) {
                cloud.getUsers().map { users ->
                    storage.insertUsers(users)
                    users
                }
            } else {
                storage.getUsers()
            }
        }.subscribeOn(schedulers.background())

    override fun getRepos(url: String?): Single<List<GithubRepos>> = network
        .onLineSingle()
        .flatMap { isOnline ->
            if (isOnline) {
                cloud.getRepos(url).map { repos ->
                    storage.insertGithubRepos(repos, url)
                    repos
                }
            } else {
                storage.getRepos(url)
            }
        }.subscribeOn(schedulers.background())

    override fun getRepo(url: String?): Single<GithubRepos> = network
        .onLineSingle()
        .flatMap { isOnline ->
            if (isOnline) {
                cloud.getRepo(url)
            } else {
                storage.getRepo(url)
            }
        }.subscribeOn(schedulers.background())
}