package com.mpowloka.pvx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

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
