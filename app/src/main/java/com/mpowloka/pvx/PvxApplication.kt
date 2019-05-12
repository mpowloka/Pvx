package com.mpowloka.pvx

import com.mpowloka.data.networking.DataDownloader
import com.mpowloka.pvx.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject

class PvxApplication : DaggerApplication() {

    @Inject
    lateinit var dataDownloader: DataDownloader

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()

        downloadData()
    }

    private fun downloadData() {
        dataDownloader.downloadData()
    }
}