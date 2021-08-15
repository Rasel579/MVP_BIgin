package com.test_app.mvp_bigin.navigation

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.test_app.mvp_bigin.model.GithubUser
import com.test_app.mvp_bigin.ui.UserFragment

class UserScreen(private val githubUser: GithubUser) {
    fun create(): Screen = FragmentScreen { UserFragment.newInstance(githubUser.login) }
}