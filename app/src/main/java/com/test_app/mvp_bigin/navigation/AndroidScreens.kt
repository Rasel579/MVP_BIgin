package com.test_app.mvp_bigin.navigation

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.test_app.mvp_bigin.ui.ItemUserFragment
import com.test_app.mvp_bigin.ui.UsersListFragment

object AndroidScreens : IScreens {
    override fun users(): Screen = FragmentScreen{UsersListFragment.newInstance()}
    override fun itemUser(pos: Int): Screen = FragmentScreen{ItemUserFragment.newInstance(pos)}
}