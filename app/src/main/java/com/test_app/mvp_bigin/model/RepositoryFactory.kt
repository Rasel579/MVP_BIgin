package com.test_app.mvp_bigin.model

import com.test_app.mvp_bigin.model.api.ApiFactory
import com.test_app.mvp_bigin.utils.schedulers.SchedulersFactory

object RepositoryFactory {
    fun create(): GithubUsersRepo = RetrofitGithubUsersRepoImpl(ApiFactory.api, SchedulersFactory.create())
}