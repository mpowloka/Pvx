package com.mpowloka.domain.items

import com.mpowloka.domain.localizations.Localization
import io.reactivex.Flowable

interface ItemsRepository {

    fun getItemsInLocalization(localization: Localization): Flowable<List<Item>>

}