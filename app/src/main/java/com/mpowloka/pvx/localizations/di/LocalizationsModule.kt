package com.mpowloka.pvx.localizations.di

import com.mpowloka.pvx.localizations.LocalizationsFragment
import com.mpowloka.pvx.localizations.list.LocalizationsRecyclerAdapter
import dagger.Module
import dagger.Provides

@Module
class LocalizationsModule {

    @Provides
    fun localizationsRecyclerAdapter(): LocalizationsRecyclerAdapter {
        return LocalizationsRecyclerAdapter()
    }

}