package com.mpowloka.data.networking.cache

import com.mpowloka.data.networking.model.ItemTypeHolderModel
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.subjects.BehaviorSubject

class ItemTypeHoldersCache {

    private val itemTypeHoldersCache = mutableMapOf<Long, ItemTypeHolderModel>()
    private val itemTypeHoldersSubject = BehaviorSubject.create<List<ItemTypeHolderModel>>()

    fun addItemTypeHolders(itemTypeHolders: List<ItemTypeHolderModel>) {
        itemTypeHolders.forEach { itemTypeHolder ->
            itemTypeHoldersCache[itemTypeHolder.itemTypeHolderId] = itemTypeHolder
        }
        itemTypeHoldersSubject.onNext(itemTypeHoldersCache.map { it.value })
    }

    fun getItemHolderTypesForLocalizationId(localizationId: Long): Flowable<List<ItemTypeHolderModel>> {
        return itemTypeHoldersSubject.map { itemTypeHolderModels ->
            itemTypeHolderModels.filter { it.holderId == localizationId }
        }.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun getItemHolderTypesForItemId(itemId: Long): Flowable<List<ItemTypeHolderModel>> {
        return itemTypeHoldersSubject.map { itemTypeHolderModels ->
            itemTypeHolderModels.filter { it.itemTypeId == itemId }
        }.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun getItemTypeTotalQuantities(): Flowable<List<ItemTypeIdWithTotalQuantity>> {
        return itemTypeHoldersSubject.map { itemTypeHolderModels ->
            itemTypeHolderModels.groupBy {
                it.itemTypeId
            }.map { entry ->
                ItemTypeIdWithTotalQuantity(
                    entry.key,
                    entry.value.sumBy { it.quantity }
                )
            }
        }.toFlowable(BackpressureStrategy.BUFFER)
    }

    data class ItemTypeIdWithTotalQuantity(
        val itemTypeId: Long,
        val totalQuantity: Int
    )

}