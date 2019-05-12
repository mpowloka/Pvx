package com.mpowloka.pvx.di

import android.app.Application
import com.mpowloka.data.di.CacheModule
import com.mpowloka.data.di.RepositoriesModule
import com.mpowloka.data.di.RetrofitModule
import com.mpowloka.pvx.PvxApplication
import com.mpowloka.pvx.di.modules.ActivityBindingModule
import com.mpowloka.pvx.di.modules.FragmentsBindingModule
import com.mpowloka.pvx.di.modules.ViewModelBindingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBindingModule::class,
        FragmentsBindingModule::class,
        RepositoriesModule::class,
        ViewModelBindingModule::class,
        CacheModule::class,
        RetrofitModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<PvxApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): ApplicationComponent.Builder

        fun build(): ApplicationComponent
    }

}