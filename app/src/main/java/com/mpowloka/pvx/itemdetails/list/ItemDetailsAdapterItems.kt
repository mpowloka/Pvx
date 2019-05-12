package com.mpowloka.pvx.itemdetails.list

import com.mpowloka.domain.localizations.Localization

sealed class ItemDetailsAdapterItem {

    data class ItemLocalization(val localization: Localization) : ItemDetailsAdapterItem()

}