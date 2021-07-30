    package com.test_app.mvp_bigin

    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import com.test_app.mvp_bigin.databinding.ActivityMainBinding
    import com.test_app.mvp_bigin.presentation.Presenter
    import com.test_app.mvp_bigin.views.MainView

    class MainActivity : AppCompatActivity(), MainView {
        private var binding : ActivityMainBinding ? = null
        private val presenter = Presenter(this)
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding?.root)
            initView()
        }

        private fun initView()=with(binding) {
            this?.btnCounter1?.setOnClickListener{presenter.firstBtnClicked()}
            this?.btnCounter2?.setOnClickListener{presenter.secondBtnClicked()}
            this?.btnCounter3?.setOnClickListener{presenter.thirdBtnClicked()}
        }

        override fun getFirstBtnValue(value: Int) {
            binding?.btnCounter1?.text  = value.toString()
        }

        override fun getSecondBtnValue(value: Int) {
            binding?.btnCounter2?.text  = value.toString()

        }

        override fun getThirdBtnValue(value: Int) {
            binding?.btnCounter3?.text  = value.toString()
        }
    }