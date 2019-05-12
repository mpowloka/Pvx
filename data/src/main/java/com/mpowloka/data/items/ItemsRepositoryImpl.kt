package com.mpowloka.data.items

import com.mpowloka.data.networking.cache.ItemTypeHoldersCache
import com.mpowloka.data.networking.cache.ItemTypesCache
import com.mpowloka.domain.items.Item
import com.mpowloka.domain.items.ItemWithTotalCount
import com.mpowloka.domain.items.ItemsRepository
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

    override fun getItemsInLocalizationWithTotalCount(localizationId: Long): Flowable<List<ItemWithTotalCount>> {
        return Flowables.combineLatest(
            itemsCache.getItemTypes(),
            itemTypeHoldersCache.getItemHolderTypesForLocalizationId(localizationId),
            itemTypeHoldersCache.getItemTypeTotalQuantities()
        ) { itemModels, itemTypeHolderModels, itemTypeQuantities ->
            val itemsInLocalizationIds = itemTypeHolderModels.map { it.itemTypeId }
            itemModels
                .filter { itemsInLocalizationIds.contains(it.itemTypeId) }
                .map { itemTypeModel ->
                    ItemWithTotalCount(
                        itemTypeModel.toItem(),
                        itemTypeQuantities.find { it.itemTypeId == itemTypeModel.itemTypeId }?.totalQuantity ?: 0
                    )
                }
        }
    }

}