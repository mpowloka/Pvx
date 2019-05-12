package com.mpowloka.pvx.main

import androidx.annotation.DrawableRes

data class ActionBarConfiguration(
    val title: String,
    val subtitle: String,
    val upButtonConfiguration: UpButtonConfiguration = UpButtonConfiguration()
)

data class UpButtonConfiguration(
    val showUpButton: Boolean = false,
    @DrawableRes val iconRes: Int = 0,
    val onClickAction: (() -> Unit)? = null
)