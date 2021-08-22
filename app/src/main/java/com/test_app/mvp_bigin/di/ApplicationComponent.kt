package com.test_app.mvp_bigin.di

import android.content.Context
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.test_app.mvp_bigin.MVPApp
import com.test_app.mvp_bigin.di.modules.GithubApiModule
import com.test_app.mvp_bigin.di.modules.GithubModule
import com.test_app.mvp_bigin.di.modules.StorageModule
import com.test_app.mvp_bigin.utils.ImageLoader
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class, GithubApiModule::class, GithubModule::class,
        StorageModule::class]
)
interface ApplicationComponent : AndroidInjector<MVPApp> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun withContext(context: Context): Builder

        @BindsInstance
        fun withRouter(router: Router): Builder

        @BindsInstance
        fun withNavigationHolder(navigatorHolder: NavigatorHolder): Builder

        @BindsInstance
        fun withImageLoader(imageLoader: ImageLoader): Builder
        fun build(): ApplicationComponent
    }
}