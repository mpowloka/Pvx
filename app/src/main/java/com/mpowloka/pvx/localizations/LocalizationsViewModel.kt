package com.mpowloka.pvx.localizations

import androidx.lifecycle.ViewModel
import com.mpowloka.domain.localizations.Localization
import com.mpowloka.domain.localizations.LocalizationsRepository
import com.mpowloka.pvx.localizations.list.LocalizationsAdapterItem
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LocalizationsViewModel @Inject constructor(
    private val localizationsRepository: LocalizationsRepository
) : ViewModel() {

    val localizations: Flowable<List<LocalizationsAdapterItem>> by lazy {

        localizationsRepository.getAllLocalizations().map { localizations ->
            localizations.map { LocalizationsAdapterItem.Localization(it.name) as LocalizationsAdapterItem }
        }

    }
}