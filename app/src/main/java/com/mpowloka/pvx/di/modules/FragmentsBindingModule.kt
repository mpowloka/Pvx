package com.mpowloka.pvx.di.modules

import com.mpowloka.pvx.di.FragmentScoped
import com.mpowloka.pvx.screens.itemdetails.ItemDetailsFragment
import com.mpowloka.pvx.screens.itemdetails.di.ItemDetailsModule
import com.mpowloka.pvx.screens.localizationdetails.LocalizationDetailsFragment
import com.mpowloka.pvx.screens.localizationdetails.di.LocalizationDetailsModule
import com.mpowloka.pvx.screens.localizations.LocalizationsFragment
import com.mpowloka.pvx.screens.localizations.di.LocalizationsModule
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

    @FragmentScoped
    @ContributesAndroidInjector(modules = [ItemDetailsModule::class])
    abstract fun itemDetailsFragment(): ItemDetailsFragment
}