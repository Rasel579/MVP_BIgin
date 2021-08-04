package com.test_app.mvp_bigin.presentation

import com.github.terrakok.cicerone.Router
import com.test_app.mvp_bigin.navigation.UsersScreen
import com.test_app.mvp_bigin.views.MainView
import moxy.MvpPresenter

class MainPresenter(
    private val router: Router) :
    MvpPresenter<MainView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.newRootScreen(UsersScreen.create())
    }

    fun back() = router.exit()
}