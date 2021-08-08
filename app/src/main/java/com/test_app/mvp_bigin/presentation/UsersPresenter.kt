package com.test_app.mvp_bigin.presentation

import com.github.terrakok.cicerone.Router
import com.test_app.mvp_bigin.model.GitHubRepo
import com.test_app.mvp_bigin.model.GithubUser
import com.test_app.mvp_bigin.navigation.UserScreen
import com.test_app.mvp_bigin.views.UserItemView
import com.test_app.mvp_bigin.views.UsersView
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class UsersPresenter(private val repo: GitHubRepo, private val router: Router) :
    MvpPresenter<UsersView>() {
    private var users = mutableListOf<GithubUser>()
    private var disposable: Disposable? = null

    /**
     * создаем презентера для adapter
     * **/
    class UserListPresenter : UserItemListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickedListener: ((UserItemView) -> Unit)? = null

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)

        }

        override fun getCount(): Int = users.size
    }

    val userListPresenter = UserListPresenter()
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    private fun loadData() {
        disposable = repo.getUsers()
            .subscribe(
                users::add,
                viewState::showError
            )
        userListPresenter.users
            .addAll(users)
        viewState.updateUsersList()
        userListPresenter.itemClickedListener = { userItemView ->
            router.navigateTo(UserScreen(users[userItemView.pos]).create())
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        disposable = null
        super.onDestroy()
    }

}