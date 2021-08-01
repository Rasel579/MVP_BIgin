package com.test_app.mvp_bigin.presentation

import com.github.terrakok.cicerone.Router
import com.test_app.mvp_bigin.navigation.IScreens
import com.test_app.mvp_bigin.views.MainView
import moxy.MvpPresenter

class MainPresenter(val router: Router, val screens : IScreens) : MvpPresenter<MainView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }
    fun backClicked(){
        router.exit()
    }
}