package com.mpowloka.data.networking.cache

import com.mpowloka.data.networking.model.ItemTypeModel
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class ItemTypesCache {

    private val itemTypesCache = mutableMapOf<Long, ItemTypeModel>()
    private val itemTypesSubject = BehaviorSubject.create<List<ItemTypeModel>>()

    fun addItemTypes(itemTypes: List<ItemTypeModel>) {
        itemTypes.forEach { itemType ->
            itemTypesCache[itemType.itemTypeId] = itemType
        }
        itemTypesSubject.onNext(itemTypesCache.map { it.value })
    }

    fun getItemForId(itemId: Long): Flowable<ItemTypeModel> {
        return itemTypesSubject.map { itemModels ->
            itemModels.find { it.itemTypeId == itemId } ?: ItemTypeModel.NO_ITEM
        }.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun getItemTypes(): Flowable<List<ItemTypeModel>> {
        return itemTypesSubject.toFlowable(BackpressureStrategy.BUFFER)
    }

}