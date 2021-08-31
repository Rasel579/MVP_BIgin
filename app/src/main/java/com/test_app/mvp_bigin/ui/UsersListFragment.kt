package com.test_app.mvp_bigin.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.Router
import com.test_app.mvp_bigin.databinding.FragmentUsersListBinding
import com.test_app.mvp_bigin.model.GithubUsersRepo
import com.test_app.mvp_bigin.presenters.UsersPresenter
import com.test_app.mvp_bigin.ui.abs.AbsFragment
import com.test_app.mvp_bigin.utils.ImageLoader
import com.test_app.mvp_bigin.utils.schedulers.Schedulers
import com.test_app.mvp_bigin.views.UsersView
import moxy.ktx.moxyPresenter
import javax.inject.Inject


class UsersListFragment : AbsFragment(), UsersView {
    private val binding: FragmentUsersListBinding by viewBinding(CreateMethod.INFLATE)
    private var adapter: UserListAdapter? = null

    @Inject
    lateinit var imageLoader: ImageLoader

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var repo: GithubUsersRepo

    @Inject
    lateinit var schedulers: Schedulers
    private val userPresenter by moxyPresenter {
        UsersPresenter(
            repo,
            router,
            schedulers
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun init() = with(binding) {
        this.recycleViewUserList.layoutManager = LinearLayoutManager(context)
        adapter = UserListAdapter(userPresenter.userListPresenter, imageLoader)
        this.recycleViewUserList.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateUsersList() {
        adapter?.notifyDataSetChanged()
    }

    override fun showError(e: Throwable) {
        Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = UsersListFragment()
    }
}