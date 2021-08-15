package com.test_app.mvp_bigin.model

object Mapper {
    fun filter(users: List<GithubUser>, login: String) = users.find { user -> user.login == login }
}