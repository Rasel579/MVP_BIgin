package com.test_app.mvp_bigin

import android.os.Bundle
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.test_app.mvp_bigin.App.Navigation.navigatorHolder
import com.test_app.mvp_bigin.App.Navigation.router
import com.test_app.mvp_bigin.databinding.ActivityMainBinding
import com.test_app.mvp_bigin.presenters.MainPresenter
import com.test_app.mvp_bigin.views.MainView
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

/**For creating an instance of moxyPresenter need to MainActivity instanced of MvpAppCompatActivity()**/
class MainActivity : MvpAppCompatActivity(), MainView {
    private val binding : ActivityMainBinding by viewBinding(CreateMethod.INFLATE)
    private val navigator = AppNavigator(this, R.id.container)
    private val mainPresenter by moxyPresenter { MainPresenter(router) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }



}