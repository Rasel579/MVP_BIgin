package com.test_app.mvp_bigin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import com.test_app.mvp_bigin.databinding.FragmentItemUserBinding
import com.test_app.mvp_bigin.model.GitHubRepo
import com.test_app.mvp_bigin.model.GithubUser
import com.test_app.mvp_bigin.presentation.UserPresenter
import com.test_app.mvp_bigin.views.UserView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class UserFragment : MvpAppCompatFragment(), UserView {
    companion object {
        private const val ARGS_USER = "ARG_USER"
        fun newInstance(githubUserLogin: String) = UserFragment().apply {
            arguments = bundleOf(ARGS_USER to githubUserLogin)
        }
    }

    private val user by lazy {
        arguments?.getString(ARGS_USER).orEmpty()
    }
    private var binding: FragmentItemUserBinding? = null
    private val userPresenter by moxyPresenter { UserPresenter(user, GitHubRepo()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemUserBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun showUser(githubUser: GithubUser) {
        binding?.userItemLogin?.text = githubUser.login
    }

    override fun showError(error: Throwable) {
        Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

}