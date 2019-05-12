package com.mpowloka.pvx.screens.localizations

import androidx.lifecycle.ViewModel
import com.mpowloka.domain.localizations.LocalizationsRepository
import com.mpowloka.pvx.screens.localizations.list.LocalizationsAdapterItem
import io.reactivex.Flowable
import javax.inject.Inject

class LocalizationsViewModel @Inject constructor(
    private val localizationsRepository: LocalizationsRepository
) : ViewModel() {

    val localizations: Flowable<List<LocalizationsAdapterItem>> by lazy {

        localizationsRepository.getAllLocalizations().map { localizations ->
            localizations.map { LocalizationsAdapterItem.LocalizationItem(it) as LocalizationsAdapterItem }
        }

    }
}