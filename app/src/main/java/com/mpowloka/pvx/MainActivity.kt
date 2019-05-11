package com.mpowloka.pvx

import android.os.Bundle
import com.mpowloka.pvx.base.BaseViewModelActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseViewModelActivity<MainViewModel>() {

    override fun getViewModelClass() = MainViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBar()
    }

    private fun setupActionBar() {
        setSupportActionBar(main_toolbar)
        supportActionBar?.setTitle(R.string.pvx)
        supportActionBar?.setSubtitle(R.string.search)
    }

}
