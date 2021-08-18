package com.test_app.mvp_bigin.views

interface RepoItemView: ItemView {
    fun setRepoName(name: String?)
    fun setLanguage(language: String?)
    fun setDate(date : String?)
}