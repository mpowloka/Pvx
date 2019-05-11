package com.mpowloka.pvx.di.modules

import com.mpowloka.pvx.di.FragmentScoped
import com.mpowloka.pvx.localizationdetails.LocalizationDetailsFragment
import com.mpowloka.pvx.localizationdetails.di.LocalizationDetailsModule
import com.mpowloka.pvx.localizations.LocalizationsFragment
import com.mpowloka.pvx.localizations.di.LocalizationsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsBindingModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = [LocalizationsModule::class])
    abstract fun localizationsFragment(): LocalizationsFragment

    @FragmentScoped
    @ContributesAndroidInjector(modules = [LocalizationDetailsModule::class])
    abstract fun localizationDetailsFragment(): LocalizationDetailsFragment
}