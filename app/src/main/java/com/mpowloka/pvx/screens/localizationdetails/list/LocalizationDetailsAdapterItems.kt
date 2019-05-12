package com.mpowloka.pvx.screens.localizationdetails.list

import com.mpowloka.domain.items.Item

sealed class LocalizationDetailsAdapterItem {

    data class ItemItem(val item: Item) : LocalizationDetailsAdapterItem()

    class NoDataItem(): LocalizationDetailsAdapterItem()

}