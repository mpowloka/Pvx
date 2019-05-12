package com.mpowloka.data.di

import com.mpowloka.data.networking.PvxApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Provides
    @Singleton
    fun pvxApi(): PvxApi {
        return PvxApi.newInstance()
    }

}