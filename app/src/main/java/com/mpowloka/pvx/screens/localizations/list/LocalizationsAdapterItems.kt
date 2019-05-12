package com.mpowloka.pvx.screens.localizations.list

import com.mpowloka.domain.localizations.Localization

sealed class LocalizationsAdapterItem {

    data class LocalizationItem(val localization: Localization) : LocalizationsAdapterItem()

}