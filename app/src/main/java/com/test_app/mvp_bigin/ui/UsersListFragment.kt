package com.test_app.mvp_bigin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.test_app.mvp_bigin.App.Navigation.router
import com.test_app.mvp_bigin.databinding.FragmentUsersListBinding
import com.test_app.mvp_bigin.model.GitHubRepo
import com.test_app.mvp_bigin.presentation.UsersPresenter
import com.test_app.mvp_bigin.views.UsersView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class UsersListFragment : MvpAppCompatFragment(), UsersView, BackButtonPressed {
    private var binding: FragmentUsersListBinding? = null
    private var adapter : UserListAdapter? = null
    private val userPresenter by moxyPresenter{ UsersPresenter(GitHubRepo(), router) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentUsersListBinding.inflate(inflater, container, false)
        return binding?.root
    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun init()= with(binding) {
        this?.recycleViewUserList?.layoutManager = LinearLayoutManager(context)
        adapter = UserListAdapter(userPresenter.userListPresenter)
        this?.recycleViewUserList?.adapter = adapter
    }

    override fun updateUsersList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() {
        userPresenter.backPressed()
    }

    override fun showError(e: Throwable) {
        Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
    }


    override fun backButtonPressed(): Boolean {
        userPresenter.backPressed()
        return true
    }

    companion object {
        fun newInstance() = UsersListFragment()
    }
}