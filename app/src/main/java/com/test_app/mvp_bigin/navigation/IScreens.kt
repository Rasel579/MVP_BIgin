package com.test_app.mvp_bigin.navigation

import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users() : Screen
    fun itemUser(pos: Int): Screen
}