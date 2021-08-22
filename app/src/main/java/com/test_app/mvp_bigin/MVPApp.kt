package com.test_app.mvp_bigin

import com.github.terrakok.cicerone.Cicerone
import com.test_app.mvp_bigin.di.DaggerApplicationComponent
import com.test_app.mvp_bigin.utils.ImageLoader
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.reactivex.plugins.RxJavaPlugins

class MVPApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<MVPApp> =
        DaggerApplicationComponent
            .builder()
            .withContext(applicationContext)
            .withImageLoader(ImageLoader)
            .apply {
                val cicerone = Cicerone.create()
                withRouter(cicerone.router)
                withNavigationHolder(cicerone.getNavigatorHolder())
            }
            .build()

    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler {}
    }
}