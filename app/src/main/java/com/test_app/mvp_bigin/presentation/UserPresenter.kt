package com.test_app.mvp_bigin.presentation

import com.test_app.mvp_bigin.model.GitHubRepo
import com.test_app.mvp_bigin.views.UserView
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class UserPresenter(
    private val userLogin: String,
    private val gitHubRepo: GitHubRepo
) : MvpPresenter<UserView>() {
    private var disposable: Disposable? = null
    override fun onFirstViewAttach() {
        disposable = gitHubRepo
            .getUsers()
            .filter { it.login == userLogin }
            .subscribe(
                viewState::showUser,
                viewState::showError
            )
    }

    override fun onDestroy() {
        disposable = null
        super.onDestroy()
    }
}

