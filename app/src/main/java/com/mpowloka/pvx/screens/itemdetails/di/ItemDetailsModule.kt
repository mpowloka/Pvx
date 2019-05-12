package com.mpowloka.pvx.screens.itemdetails.di

import com.mpowloka.pvx.screens.itemdetails.ItemDetailsFragment
import com.mpowloka.pvx.screens.itemdetails.list.ItemDetailsRecyclerAdapter
import dagger.Module
import dagger.Provides

@Module
class ItemDetailsModule {

    @Provides
    fun itemDetailsRecyclerAdapter(itemDetailsFragment: ItemDetailsFragment): ItemDetailsRecyclerAdapter {
        return ItemDetailsRecyclerAdapter(itemDetailsFragment)
    }

}