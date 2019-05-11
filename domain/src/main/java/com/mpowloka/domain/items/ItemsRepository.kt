package com.mpowloka.domain.items

import io.reactivex.Flowable

interface ItemsRepository {

    fun getItemsInLocalization(localizationId: Long): Flowable<List<Item>>

}