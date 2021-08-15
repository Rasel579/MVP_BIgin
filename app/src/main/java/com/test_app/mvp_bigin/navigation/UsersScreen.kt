package com.test_app.mvp_bigin.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.test_app.mvp_bigin.ui.UsersListFragment

object UsersScreen {
    fun create() = FragmentScreen { UsersListFragment.newInstance() }
}