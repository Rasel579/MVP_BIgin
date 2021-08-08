package com.test_app.mvp_bigin.presentation

import com.github.terrakok.cicerone.Router
import com.test_app.mvp_bigin.model.GitHubRepo
import com.test_app.mvp_bigin.model.GithubUser
import com.test_app.mvp_bigin.navigation.AndroidScreens
import com.test_app.mvp_bigin.views.UserItemView
import com.test_app.mvp_bigin.views.UsersView
import moxy.MvpPresenter
class UsersPresenter(private val model : GitHubRepo, val router: Router, val screens : AndroidScreens) : MvpPresenter<UsersView>() {
    //создаем презентера для adapter
    class UserListPresenter : UserItemListPresenter{
        val users = mutableListOf<GithubUser>()
        override var itemClickedListener: ((UserItemView) -> Unit)? = null

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)

        }
        override fun getCount(): Int  = users.size
    }
    val userListPresenter = UserListPresenter()
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        userListPresenter.itemClickedListener = { userItemView ->
          router.navigateTo(screens.itemUser(userItemView.pos))
        }
    }

     fun loadData() {
        val users = model.getUsers()
        userListPresenter.users.addAll(users)
        viewState.updateUsersList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}