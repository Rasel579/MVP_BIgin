package com.test_app.mvp_bigin.di.modules

import com.test_app.mvp_bigin.model.GithubUsersRepo
import com.test_app.mvp_bigin.model.RepositoryImpl
import com.test_app.mvp_bigin.model.network.AndroidNetworkStatus
import com.test_app.mvp_bigin.model.network.NetworkStatus
import com.test_app.mvp_bigin.model.retrofit.CloudSource
import com.test_app.mvp_bigin.model.retrofit.RetrofitGithubUsersRepoImpl
import com.test_app.mvp_bigin.model.storage.RoomRepositoryImpl
import com.test_app.mvp_bigin.model.storage.DataSource
import com.test_app.mvp_bigin.ui.MainActivity
import com.test_app.mvp_bigin.ui.RepoFragment
import com.test_app.mvp_bigin.ui.UserFragment
import com.test_app.mvp_bigin.ui.UsersListFragment
import com.test_app.mvp_bigin.utils.schedulers.DefaultSchedulers
import com.test_app.mvp_bigin.utils.schedulers.Schedulers
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
interface GithubModule {
    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    fun bindUsersFragment(): UsersListFragment

    @ContributesAndroidInjector
    fun bindUserFragment(): UserFragment

    @ContributesAndroidInjector
    fun bindRepoFragment(): RepoFragment


    @Binds
    fun bindRepository(repository: RepositoryImpl): GithubUsersRepo

    @Binds
    fun bindCloudStorage(cloud: RetrofitGithubUsersRepoImpl): CloudSource

    @Binds
    fun bindCacheStorage(storage: RoomRepositoryImpl): DataSource

    @Binds
    fun bindNetworkStatus(network: AndroidNetworkStatus): NetworkStatus

    @Binds
    fun bindScheduler(schedulers: DefaultSchedulers): Schedulers


}