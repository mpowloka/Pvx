package com.mpowloka.pvx.screens.itemdetails.list

import com.mpowloka.domain.localizations.Localization

sealed class ItemDetailsAdapterItem {

    data class ItemLocalization(val localization: Localization) : ItemDetailsAdapterItem()

    class NoDataItem(): ItemDetailsAdapterItem()

}