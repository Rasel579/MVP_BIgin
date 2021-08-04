package com.test_app.mvp_bigin.views

import com.test_app.mvp_bigin.model.GithubUser
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserView : MvpView {
    fun showUser(githubUser: GithubUser)
}