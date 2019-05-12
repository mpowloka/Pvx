package com.mpowloka.pvx.main

import android.os.Bundle
import android.view.MenuItem
import com.mpowloka.pvx.R
import com.mpowloka.pvx.base.BaseViewModelActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseViewModelActivity<MainViewModel>() {

    @Inject
    lateinit var actionBarController: ActionBarController

    override fun getViewModelClass() = MainViewModel::class.java

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            actionBarController.onUpButtonClicked()
            true
        }

        else -> super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBar()
    }

    override fun onResume() {
        super.onResume()

        displayActionBarFromConfiguration()
    }

    private fun setupActionBar() {
        setSupportActionBar(main_toolbar)
        supportActionBar?.let {
            actionBarController.setupActionBarInitialState(it)
        }
    }

    @Suppress("UnstableApiUsage")
    private fun displayActionBarFromConfiguration() {

        viewModel.actionBarConfiguration
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .`as`(autoDisposable())
            .subscribe(
                { configuration ->
                    actionBarController.setupActionBarFromConfiguration(
                        supportActionBar ?: return@subscribe,
                        configuration
                    )
                },
                { exception ->
                    exception.printStackTrace()
                }
            )
    }



}
