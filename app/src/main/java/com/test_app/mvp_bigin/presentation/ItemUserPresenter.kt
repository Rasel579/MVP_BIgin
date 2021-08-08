package com.test_app.mvp_bigin.presentation

import com.test_app.mvp_bigin.model.GitHubRepo
import com.test_app.mvp_bigin.views.ItemUserView
import moxy.MvpPresenter

class ItemUserPresenter(private val model : GitHubRepo): MvpPresenter<ItemUserView>() {
   fun setLogin(pos : Int) = model.getUsers()[pos]
}