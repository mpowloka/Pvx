package com.mpowloka.data.items

import com.mpowloka.data.networking.cache.ItemTypeHoldersCache
import com.mpowloka.data.networking.cache.ItemTypesCache
import com.mpowloka.domain.items.Item
import com.mpowloka.domain.items.ItemsRepository
import com.mpowloka.domain.localizations.Localization
import io.reactivex.Flowable
import io.reactivex.rxkotlin.Flowables
import javax.inject.Inject

class ItemsRepositoryImpl @Inject constructor(
    private val itemsCache: ItemTypesCache,
    private val itemTypeHoldersCache: ItemTypeHoldersCache
) : ItemsRepository {

    override fun getItemForId(id: Long): Flowable<Item> {
        return itemsCache.getItemForId(id).map { it.toItem() }
    }

    override fun getItemsInLocalization(localizationId: Long): Flowable<List<Item>> {
        return Flowables.combineLatest(
            itemsCache.getItemTypes(),
            itemTypeHoldersCache.getItemHolderTypesForLocalizationId(localizationId)
        ) { itemModels, itemTypeHolderModels ->
            val itemsInLocalizationIds = itemTypeHolderModels.map { it.itemTypeId }
            itemModels.filter { itemsInLocalizationIds.contains(it.itemTypeId) }.map { it.toItem() }
        }
    }

}