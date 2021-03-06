package com.test_app.mvp_bigin.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.test_app.mvp_bigin.R
import com.test_app.mvp_bigin.databinding.FragmentRepoBinding
import com.test_app.mvp_bigin.model.GithubUsersRepo
import com.test_app.mvp_bigin.model.retrofit.GithubRepos
import com.test_app.mvp_bigin.presenters.RepoPresenter
import com.test_app.mvp_bigin.ui.abs.AbsFragment
import com.test_app.mvp_bigin.utils.schedulers.Schedulers
import com.test_app.mvp_bigin.views.RepoView
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class RepoFragment : AbsFragment(R.layout.fragment_repo), RepoView {
    companion object {
        private const val ARG_REPO_URL = "repo"
        fun newInstance(repoUrl: String?) =
            RepoFragment().apply {
                arguments = bundleOf(ARG_REPO_URL to repoUrl)
            }
    }

    private val repoUrl: String? by lazy {
        arguments?.getString(ARG_REPO_URL)
    }
    private val binding: FragmentRepoBinding by viewBinding(CreateMethod.INFLATE)

    @Inject
    lateinit var repo: GithubUsersRepo

    @Inject
    lateinit var schedulers: Schedulers

    private val presenter: RepoPresenter by moxyPresenter {
        RepoPresenter(
            repo,
            repoUrl,
            schedulers
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun showRepoName(repo: GithubRepos) {
        binding.forks.text = "Repo forks is ${repo.forks}"
    }

    @SuppressLint("SetTextI18n")
    override fun showRepoForks(repo: GithubRepos) {
        binding.forks.text = "Repo Name is ${repo.name}"
    }

    @SuppressLint("SetTextI18n")
    override fun showRepoDate(repo: GithubRepos) {
        binding.date.text = "Creted at ${repo.date}"
    }

    @SuppressLint("SetTextI18n")
    override fun showRepoLanguage(repo: GithubRepos) {
        binding.language.text = "Using languages ${repo.language}"
    }

    override fun showError(error: Throwable) {
        Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
    }
}