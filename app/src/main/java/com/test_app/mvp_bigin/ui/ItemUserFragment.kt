package com.test_app.mvp_bigin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test_app.mvp_bigin.App
import com.test_app.mvp_bigin.databinding.FragmentItemUserBinding
import com.test_app.mvp_bigin.model.GitHubRepo
import com.test_app.mvp_bigin.presentation.ItemUserPresenter
import com.test_app.mvp_bigin.views.ItemUserView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class ItemUserFragment(val pos: Int) : MvpAppCompatFragment(), ItemUserView {
    var binding : FragmentItemUserBinding? = null
    private val itemUserPresenter by moxyPresenter{ ItemUserPresenter(GitHubRepo(), App.instance.router) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemUserBinding.inflate(inflater, container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLogin()
    }

    override fun setLogin() {
         binding?.userItemLogin?.text = itemUserPresenter.setLogin(pos).toString()
    }

    companion object {
        fun newInstance(pos: Int) = ItemUserFragment(pos)
    }
}