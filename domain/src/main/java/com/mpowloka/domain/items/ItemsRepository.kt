package com.mpowloka.domain.items

import io.reactivex.Flowable

interface ItemsRepository {

    fun getItemForId(id: Long): Flowable<Item>

    fun getItemsInLocalizationWithTotalCount(localizationId: Long): Flowable<List<ItemWithTotalCount>>

}