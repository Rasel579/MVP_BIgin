package com.test_app.mvp_bigin.presentation

import com.test_app.mvp_bigin.model.MainModel
import com.test_app.mvp_bigin.views.MainView

class Presenter(val view : MainView) {
    private val model = MainModel()
    fun firstBtnClicked(){
        val value = model.incrementCounter(0)
        view.getFirstBtnValue(value)
    }
    fun secondBtnClicked(){
        val value = model.incrementCounter(1)
        view.getSecondBtnValue(value)
    }
    fun thirdBtnClicked(){
        val value = model.incrementCounter(2)
        view.getThirdBtnValue(value)
    }

}