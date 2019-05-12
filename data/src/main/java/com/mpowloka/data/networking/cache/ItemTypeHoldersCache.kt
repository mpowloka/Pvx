package com.mpowloka.data.networking.cache

import com.mpowloka.data.networking.model.ItemTypeHolderModel
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject
import javax.inject.Singleton

class ItemTypeHoldersCache {

    private val itemTypeHoldersCache = mutableMapOf<Long, ItemTypeHolderModel>()
    private val itemTypeHoldersSubject = BehaviorSubject.create<List<ItemTypeHolderModel>>()

    fun addItemTypeHolders(itemTypeHolders: List<ItemTypeHolderModel>) {
        itemTypeHolders.forEach { itemTypeHolder ->
            itemTypeHoldersCache[itemTypeHolder.itemTypeHolderId] = itemTypeHolder
        }
        itemTypeHoldersSubject.onNext(itemTypeHoldersCache.map { it.value })
    }

}