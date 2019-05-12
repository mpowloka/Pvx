package com.mpowloka.pvx.main

import org.junit.Before
import org.junit.Test

class MainViewModelTest {

    private lateinit var SUT: MainViewModel

    @Before
    fun setUp() {
        SUT = MainViewModel()
    }

    @Test
    fun setActionBarConfiguration_configurationEmitted() {
        SUT.setActionBarConfiguration(ACTION_BAR_CONFIGURATION)

        SUT.actionBarConfiguration.test().assertValue(
            ACTION_BAR_CONFIGURATION
        )
    }

    companion object {

        private val ACTION_BAR_CONFIGURATION = ActionBarConfiguration("title", "subtitle")

    }
}