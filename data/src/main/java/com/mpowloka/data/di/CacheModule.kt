package com.mpowloka.data.di

import com.mpowloka.data.networking.cache.HoldersCache
import com.mpowloka.data.networking.cache.ItemTypeHoldersCache
import com.mpowloka.data.networking.cache.ItemTypesCache
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Provides
    @Singleton
    fun holdersCache(): HoldersCache {
        return HoldersCache()
    }

    @Provides
    @Singleton
    fun itemTypeHoldersCache(): ItemTypeHoldersCache {
        return ItemTypeHoldersCache()
    }

    @Provides
    @Singleton
    fun itemTypesCache(): ItemTypesCache {
        return ItemTypesCache()
    }

}