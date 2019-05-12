package com.mpowloka.pvx.screens.localizations.di

import com.mpowloka.pvx.screens.localizations.LocalizationsFragment
import com.mpowloka.pvx.screens.localizations.list.LocalizationsRecyclerAdapter
import dagger.Module
import dagger.Provides

@Module
class LocalizationsModule {

    @Provides
    fun localizationsRecyclerAdapter(localizationsFragment: LocalizationsFragment): LocalizationsRecyclerAdapter {
        return LocalizationsRecyclerAdapter(localizationsFragment)
    }

}