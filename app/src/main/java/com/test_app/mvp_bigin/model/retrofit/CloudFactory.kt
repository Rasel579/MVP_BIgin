package com.test_app.mvp_bigin.model.retrofit

import com.test_app.mvp_bigin.model.api.ApiFactory
import com.test_app.mvp_bigin.utils.schedulers.SchedulersFactory

object CloudFactory {
    fun create() : CloudSource = RetrofitGithubUsersRepoImpl(ApiFactory.api, SchedulersFactory.create())
}