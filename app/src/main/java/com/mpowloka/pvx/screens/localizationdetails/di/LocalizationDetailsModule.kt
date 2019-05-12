package com.mpowloka.pvx.screens.localizationdetails.di

import com.mpowloka.pvx.screens.localizationdetails.LocalizationDetailsFragment
import com.mpowloka.pvx.screens.localizationdetails.list.LocalizationDetailsRecyclerAdapter
import dagger.Module
import dagger.Provides

@Module
class LocalizationDetailsModule {

    @Provides
    fun localizationDetailsRecyclerAdapter(localizationDetailsFragment: LocalizationDetailsFragment): LocalizationDetailsRecyclerAdapter {
        return LocalizationDetailsRecyclerAdapter(
            localizationDetailsFragment
        )
    }

}