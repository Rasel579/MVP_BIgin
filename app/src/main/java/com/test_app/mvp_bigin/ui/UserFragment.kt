package com.test_app.mvp_bigin.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.Router
import com.test_app.mvp_bigin.R
import com.test_app.mvp_bigin.databinding.FragmentItemUserBinding
import com.test_app.mvp_bigin.model.GithubUsersRepo
import com.test_app.mvp_bigin.model.retrofit.GithubUser
import com.test_app.mvp_bigin.presenters.UserPresenter
import com.test_app.mvp_bigin.ui.abs.AbsFragment
import com.test_app.mvp_bigin.utils.ImageLoader
import com.test_app.mvp_bigin.utils.schedulers.Schedulers
import com.test_app.mvp_bigin.views.UserView
import moxy.ktx.moxyPresenter
import javax.inject.Inject


class UserFragment : AbsFragment(R.layout.fragment_users_list), UserView {
    companion object {
        private const val ARGS_USER = "ARG_USER"
        fun newInstance(githubUserLogin: String?) = UserFragment().apply {
            arguments = bundleOf(ARGS_USER to githubUserLogin)
        }
    }

    @Inject
    lateinit var repository: GithubUsersRepo

    @Inject
    lateinit var schedulers: Schedulers

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var imageLoader: ImageLoader
    private val binding by viewBinding<FragmentItemUserBinding>(CreateMethod.INFLATE)
    private val user by lazy {
        arguments?.getString(ARGS_USER).orEmpty()
    }
    private val userPresenter by moxyPresenter {
        UserPresenter(
            user,
            repository,
            schedulers,
            router
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun showUser(githubUser: GithubUser) {
        binding.userItemLogin.text = githubUser.login
    }

    override fun showAvatar(githubUser: GithubUser) {
        githubUser.avatarUrl?.let { imageLoader.load(it, binding.avatarImage) }
    }

    override fun init() {
        binding.reposList.layoutManager = LinearLayoutManager(context)
        binding.reposList.adapter = RepoListAdapter(userPresenter.reposPresenter)
    }

    override fun showError(error: Throwable) {
        Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun updateRepose() {
        binding.reposList.adapter?.notifyDataSetChanged()
    }
}