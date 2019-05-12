package com.mpowloka.pvx.itemdetails.di

import com.mpowloka.pvx.itemdetails.ItemDetailsFragment
import com.mpowloka.pvx.itemdetails.list.ItemDetailsRecyclerAdapter
import dagger.Module
import dagger.Provides

@Module
class ItemDetailsModule {

    @Provides
    fun itemDetailsRecyclerAdapter(itemDetailsFragment: ItemDetailsFragment): ItemDetailsRecyclerAdapter {
        return ItemDetailsRecyclerAdapter(itemDetailsFragment)
    }

}