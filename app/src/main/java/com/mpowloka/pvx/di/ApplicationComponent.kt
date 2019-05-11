package com.mpowloka.pvx.di

import android.app.Application
import com.mpowloka.data.di.RepositoriesModule
import com.mpowloka.pvx.PvxApplication
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
        RepositoriesModule::class
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