package com.test_app.mvp_bigin.model

class MainModel {
  private val counter = mutableListOf(0,0,0)
  fun incrementCounter(index: Int) = ++counter[index]
}