package com.test_app.mvp_bigin.presentation

import com.test_app.mvp_bigin.model.GithubUsersRepo
import com.test_app.mvp_bigin.utils.schedulers.Schedulers
import com.test_app.mvp_bigin.views.RepoView
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter

class RepoPresenter(
    private val repo : GithubUsersRepo,
    private val repoUrl: String?,
    private val schedulers: Schedulers
) : MvpPresenter<RepoView>(){
    private val disposable = CompositeDisposable()
    override fun onFirstViewAttach() {
         disposable += repo
             .getRepo(repoUrl)
             .observeOn(schedulers.main())
             .subscribe(
                 {
                   viewState.showRepoName(it)
                   viewState.showRepoForks(it)
                   viewState.showRepoDate(it)
                 },{}
             )
    }
}