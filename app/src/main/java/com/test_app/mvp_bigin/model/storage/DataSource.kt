package com.test_app.mvp_bigin.model.storage

import com.test_app.mvp_bigin.model.GithubUsersRepo
import com.test_app.mvp_bigin.model.retrofit.GithubRepos
import com.test_app.mvp_bigin.model.retrofit.GithubUser
import io.reactivex.rxjava3.core.Single

interface DataSource  {
    fun insertUsers(users: List<GithubUser>)
    fun insertGithubRepos(repos: List<GithubRepos>, usersUrl: String?)
    fun getUsers(): Single<List<GithubUser>>
    fun getRepos(url : String?): Single<List<GithubRepos>>
    fun getRepo(url : String?): Single<GithubRepos>
}