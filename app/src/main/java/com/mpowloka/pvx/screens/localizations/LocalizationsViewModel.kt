package com.mpowloka.pvx.screens.localizations

import androidx.lifecycle.ViewModel
import com.mpowloka.domain.localizations.LocalizationsRepository
import com.mpowloka.pvx.screens.localizations.list.LocalizationsAdapterItem
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.rxkotlin.Flowables
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class LocalizationsViewModel @Inject constructor(
    private val localizationsRepository: LocalizationsRepository
) : ViewModel() {

    private val filterPhraseSubject = BehaviorSubject.createDefault("")

    val adapterItems: Flowable<List<LocalizationsAdapterItem>> by lazy {

        Flowables.combineLatest(
            localizationsRepository.getAllLocalizations(),
            filterPhraseSubject.toFlowable(BackpressureStrategy.BUFFER)
        ) { localizations, phrase ->
            localizations
                .filter { it.name.contains(phrase) }
                .map { LocalizationsAdapterItem.LocalizationItem(it) as LocalizationsAdapterItem }
        }

    }

    fun filterPhraseTyped(phrase: String) {
        filterPhraseSubject.onNext(phrase)
    }
}