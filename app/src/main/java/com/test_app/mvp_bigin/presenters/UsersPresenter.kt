package com.test_app.mvp_bigin.presenters

import com.github.terrakok.cicerone.Router
import com.test_app.mvp_bigin.model.retrofit.GithubUser
import com.test_app.mvp_bigin.model.GithubUsersRepo
import com.test_app.mvp_bigin.navigation.UserScreen
import com.test_app.mvp_bigin.utils.schedulers.Schedulers
import com.test_app.mvp_bigin.views.UserItemView
import com.test_app.mvp_bigin.views.UsersView
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter

class UsersPresenter(
    private val repo: GithubUsersRepo,
    private val router: Router,
    private val uiScheduler: Schedulers
) :
    MvpPresenter<UsersView>() {
    private var users = mutableListOf<GithubUser>()
    private var disposable = CompositeDisposable()

    /** создаем презентера для adapter's **/
    class UserListPresenter(private val router: Router) : UserItemListPresenter {
        val user = mutableListOf<GithubUser>()
        override var itemClickedListener: ((UserItemView) -> Unit)? = null

        override fun bindView(view: UserItemView) {
            val user = user[view.pos]
            user.login?.let { view.setLogin(it) }
            user.avatarUrl?.let { view.setAvatar(it) }

        }

        override fun getCount(): Int = user.size
    }

    val userListPresenter = UserListPresenter(router)
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    private fun loadData() {
        disposable += repo
            .getUsers().observeOn(uiScheduler.main())
            .subscribe(
                {
                    users.addAll(it)
                    userListPresenter.user.addAll(users)
                    viewState.updateUsersList()
                },
                viewState::showError
            )
        userListPresenter.itemClickedListener = { userItemView ->
            router.navigateTo(UserScreen(users[userItemView.pos]).create())
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        disposable.clear()
    }

}