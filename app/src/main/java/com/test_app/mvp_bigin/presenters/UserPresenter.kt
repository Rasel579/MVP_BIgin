package com.test_app.mvp_bigin.presenters

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.test_app.mvp_bigin.model.retrofit.GithubRepos
import com.test_app.mvp_bigin.model.retrofit.GithubUser
import com.test_app.mvp_bigin.model.GithubUsersRepo
import com.test_app.mvp_bigin.model.Mapper
import com.test_app.mvp_bigin.navigation.RepoScreen
import com.test_app.mvp_bigin.utils.schedulers.Schedulers
import com.test_app.mvp_bigin.views.RepoItemView
import com.test_app.mvp_bigin.views.UserView
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter

class UserPresenter(
    private val userLogin: String,
    private val gitHubRepo: GithubUsersRepo,
    private val schedulers: Schedulers,
    private val router: Router
) : MvpPresenter<UserView>() {
    private val repos = mutableListOf<GithubRepos>()
    private var disposable = CompositeDisposable()
    val reposPresenter = ReposPresenter()
    override fun onFirstViewAttach() {
        viewState.init()
        disposable += gitHubRepo
            .getUsers()
            .map { Mapper.filter(it, userLogin) }
            .observeOn(schedulers.main())
            .subscribe(
                {
                    it?.let { user ->
                        viewState.showUser(user)
                        viewState.showAvatar(user)
                        loadReposData(user)
                    }
                },
                viewState::showError
            )
    }

    private fun loadReposData(user: GithubUser) {
        disposable += gitHubRepo
            .getRepos(user.reposUrl)
            .observeOn(schedulers.main())
            .subscribe(
                {
                    repos.addAll(it)
                    reposPresenter.repos.addAll(repos)
                    viewState.updateRepose()

                },
                viewState::showError
            )
        reposPresenter.itemClickedListener = {view ->
            router.navigateTo(RepoScreen(repos[view.pos]).create())
        }
    }

    class ReposPresenter() : ItemListPresenter<RepoItemView>{
        val repos = mutableListOf<GithubRepos>()

        override fun bindView(view: RepoItemView) {
            val repo = repos[view.pos]
            view.setRepoName(repo.name)
            view.setLanguage(repo.language)
            view.setDate(repo.date)
            Log.e("repos", repo.toString())
        }
        override fun getCount(): Int = repos.size
        override var itemClickedListener: ((RepoItemView) -> Unit)? = null

    }

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }
}

