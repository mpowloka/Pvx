package com.mpowloka.data.networking.cache

import com.mpowloka.data.networking.model.HolderModel
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
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

    fun getHolders(): Flowable<List<HolderModel>> {
        return holdersSubject.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun getHolderForId(holderId: Long): Flowable<HolderModel> {
        return holdersSubject.map {
            it.find { it.holderId == holderId } ?: HolderModel.NO_HOLDER
        }.toFlowable(BackpressureStrategy.BUFFER)
    }

}