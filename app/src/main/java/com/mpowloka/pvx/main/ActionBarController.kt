package com.mpowloka.pvx.main

import android.content.res.Resources
import androidx.appcompat.app.ActionBar
import com.mpowloka.pvx.R
import javax.inject.Inject

class ActionBarController @Inject constructor() {

    private var onUpButtonClicked: (() -> Unit)? = null

    fun onUpButtonClicked() = onUpButtonClicked?.invoke()

    fun setupActionBarInitialState(actionBar: ActionBar) {
        actionBar.setTitle(R.string.pvx)
        actionBar.setSubtitle(R.string.all_localizations)
    }

    fun setupActionBarFromConfiguration(
        actionBar: ActionBar,
        configuration: ActionBarConfiguration
    ) {
        when (configuration.upButtonConfiguration.showUpButton) {

            false -> {

                actionBar.setDisplayShowHomeEnabled(false)
                actionBar.setDisplayHomeAsUpEnabled(false)
            }

            true -> {
                actionBar.setDisplayHomeAsUpEnabled(true)
                actionBar.setDisplayShowHomeEnabled(true)
                actionBar.setHomeAsUpIndicator(configuration.upButtonConfiguration.iconRes)
            }

        }
        onUpButtonClicked = configuration.upButtonConfiguration.onClickAction
        actionBar.title = configuration.title
        actionBar.subtitle = configuration.subtitle
    }

}