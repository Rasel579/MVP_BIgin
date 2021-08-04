package com.test_app.mvp_bigin.presentation

import com.test_app.mvp_bigin.model.GitHubRepo
import com.test_app.mvp_bigin.views.UserView
import moxy.MvpPresenter

class UserPresenter(
    private val userLogin: String,
    private val gitHubRepo: GitHubRepo
) : MvpPresenter<UserView>() {
    override fun onFirstViewAttach() {
        gitHubRepo.getUsers()
            .firstOrNull { user -> user.login == userLogin }
            ?.let (viewState::showUser )
    }
}