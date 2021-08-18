package com.test_app.mvp_bigin.navigation

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.test_app.mvp_bigin.model.retrofit.GithubRepos
import com.test_app.mvp_bigin.ui.RepoFragment

class RepoScreen(private val repo: GithubRepos) {
    fun create() : Screen = FragmentScreen{ RepoFragment.newInstance(repo.url)}
}