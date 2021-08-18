package com.test_app.mvp_bigin.model.storage

import com.test_app.mvp_bigin.model.GithubUsersRepo
import com.test_app.mvp_bigin.model.retrofit.GithubRepos
import com.test_app.mvp_bigin.model.retrofit.GithubUser

interface Storage : GithubUsersRepo {
    fun insertUsers(users: List<GithubUser>)
    fun insertGithubRepos(repos: List<GithubRepos>, usersUrl: String?)
}