package com.test_app.mvp_bigin.model

class MainModel {
    private val counter = mutableListOf(0, 0, 0)
    fun getCounter(index: Int) = counter[index]
    fun incrementCounter(index: Int) = ++counter[index]
    fun setCounter(index: Int, value: Int) = counter.set(index, value)
}