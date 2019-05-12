package com.mpowloka.domain.items

import io.reactivex.Flowable

interface ItemsRepository {

    fun getItemForId(id: Long): Flowable<Item>

    fun getItemsInLocalization(localizationId: Long): Flowable<List<Item>>

}