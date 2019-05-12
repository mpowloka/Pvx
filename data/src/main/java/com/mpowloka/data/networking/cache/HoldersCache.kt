package com.mpowloka.data.networking.cache

import com.mpowloka.data.networking.model.HolderModel
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject
import javax.inject.Singleton

class HoldersCache {

    private val holdersCache = mutableMapOf<Long, HolderModel>()
    private val holdersSubject = BehaviorSubject.create<List<HolderModel>>()

    fun addHolders(holders: List<HolderModel>) {
        holders.forEach { holder ->
            holdersCache[holder.holderId] = holder
        }
        holdersSubject.onNext(holdersCache.map { it.value })
    }

}