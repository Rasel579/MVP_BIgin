package com.test_app.mvp_bigin

import android.os.Bundle
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.test_app.mvp_bigin.databinding.ActivityMainBinding
import com.test_app.mvp_bigin.navigation.AndroidScreens
import com.test_app.mvp_bigin.presentation.MainPresenter
import com.test_app.mvp_bigin.ui.BackButtonPressed
import com.test_app.mvp_bigin.views.MainView
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
//For creating an instance of moxyPresenter need to MainActivity instanced of MvpAppCompatActivity()
class MainActivity : MvpAppCompatActivity(), MainView {
    private var binding : ActivityMainBinding ? = null
    private val navigator = AppNavigator(this, R.id.container)
    private val mainPresenter by moxyPresenter{MainPresenter(App.instance.router, AndroidScreens)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

    override fun onResumeFragments() {
      super.onResumeFragments()
      App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if(it is BackButtonPressed && it.backButtonPressed()){
                return
            }
            mainPresenter.backClicked()
        }
    }

}