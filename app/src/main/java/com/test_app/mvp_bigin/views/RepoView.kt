package com.test_app.mvp_bigin.views

import com.test_app.mvp_bigin.model.retrofit.GithubRepos
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RepoView : MvpView {
    fun showRepoName(repo: GithubRepos)
    fun showRepoForks(repo: GithubRepos)
    fun showRepoDate(repo: GithubRepos)
    fun showRepoLanguage(repo: GithubRepos)
    fun showError(error: Throwable)
}