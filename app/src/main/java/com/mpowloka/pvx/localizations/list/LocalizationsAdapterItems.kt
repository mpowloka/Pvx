package com.mpowloka.pvx.localizations.list

sealed class LocalizationsAdapterItem {

    class Localization(val localizationName: String) : LocalizationsAdapterItem()

}