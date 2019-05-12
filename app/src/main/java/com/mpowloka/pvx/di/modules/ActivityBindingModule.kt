package com.mpowloka.pvx.di.modules

import com.mpowloka.pvx.main.MainActivity
import com.mpowloka.pvx.di.ActivityScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

}