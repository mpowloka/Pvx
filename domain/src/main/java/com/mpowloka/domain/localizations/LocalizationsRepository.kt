package com.mpowloka.domain.localizations

import io.reactivex.Flowable

interface LocalizationsRepository {

    fun getLocalizationForId(id: Long): Flowable<Localization>

    fun getAllLocalizations(): Flowable<List<Localization>>

    fun getLocalizationsWithItem(itemId: Long): Flowable<List<Localization>>

}