package com.test_app.mvp_bigin.views

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UsersView : MvpView{
    fun init()
    fun updateUsersList()
    fun backPressed()
}