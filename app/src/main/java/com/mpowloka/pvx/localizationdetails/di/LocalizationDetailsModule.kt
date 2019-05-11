package com.mpowloka.pvx.localizationdetails.di

import com.mpowloka.pvx.localizationdetails.LocalizationDetailsFragment
import com.mpowloka.pvx.localizationdetails.list.LocalizationDetailsRecyclerAdapter
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