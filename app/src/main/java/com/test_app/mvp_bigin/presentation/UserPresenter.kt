package com.test_app.mvp_bigin.presentation

import com.test_app.mvp_bigin.model.GitHubRepo
import com.test_app.mvp_bigin.views.UserView
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter

class UserPresenter(
    private val userLogin: String,
    private val gitHubRepo: GitHubRepo
) : MvpPresenter<UserView>() {
    private var disposable = CompositeDisposable()
    override fun onFirstViewAttach() {
        disposable += gitHubRepo
            .getUsers()
            .map { it.find { user ->  user.login == userLogin } }
            .subscribe(
                { it?.let { user -> viewState.showUser(user) } },
                viewState::showError
            )
    }

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }
}

