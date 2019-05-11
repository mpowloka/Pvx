package com.mpowloka.domain.localizations

import io.reactivex.Flowable

interface LocalizationsRepository {

    fun getAllLocalizations(): Flowable<List<Localization>>

}