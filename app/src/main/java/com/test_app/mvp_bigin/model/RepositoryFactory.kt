package com.test_app.mvp_bigin.model

import com.test_app.mvp_bigin.model.network.NetworkStatus
import com.test_app.mvp_bigin.model.retrofit.CloudFactory
import com.test_app.mvp_bigin.model.storage.StorageFactory
import com.test_app.mvp_bigin.utils.schedulers.SchedulersFactory

object RepositoryFactory {
    fun create(networkStatus: NetworkStatus): GithubUsersRepo =
        RepositoryImpl(
            CloudFactory.create(),
            StorageFactory.create(),
            networkStatus,
            SchedulersFactory.create()
        )
}