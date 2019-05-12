package com.mpowloka.data.networking.cache

import com.mpowloka.data.networking.model.ItemTypeModel
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject
import javax.inject.Singleton

class ItemTypesCache {

    private val itemTypesCache = mutableMapOf<Long, ItemTypeModel>()
    private val itemTypesSubject = BehaviorSubject.create<List<ItemTypeModel>>()

    fun addItemTypes(itemTypes: List<ItemTypeModel>) {
        itemTypes.forEach { itemType ->
            itemTypesCache[itemType.itemTypeId] = itemType
        }
        itemTypesSubject.onNext(itemTypesCache.map { it.value })
    }

}