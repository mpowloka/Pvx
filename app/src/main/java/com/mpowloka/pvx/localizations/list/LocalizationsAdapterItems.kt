package com.mpowloka.pvx.localizations.list

sealed class LocalizationsAdapterItem {

    data class Localization(val localizationName: String) : LocalizationsAdapterItem()

}