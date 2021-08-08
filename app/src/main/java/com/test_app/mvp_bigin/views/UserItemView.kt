package com.test_app.mvp_bigin.views

import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserItemView : ItemView {
    fun setLogin(login : String)
}