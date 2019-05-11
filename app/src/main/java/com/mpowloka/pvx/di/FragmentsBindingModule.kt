package com.mpowloka.pvx.di

import com.mpowloka.pvx.localizations.LocalizationsFragment
import com.mpowloka.pvx.localizations.di.LocalizationsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsBindingModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = [LocalizationsModule::class])
    abstract fun localizationsFragment(): LocalizationsFragment
}