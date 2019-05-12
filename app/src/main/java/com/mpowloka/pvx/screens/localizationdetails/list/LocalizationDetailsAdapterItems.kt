package com.mpowloka.pvx.screens.localizationdetails.list

import com.mpowloka.domain.items.ItemWithTotalCount

sealed class LocalizationDetailsAdapterItem {

    data class ItemItem(val item: ItemWithTotalCount) : LocalizationDetailsAdapterItem()

    class NoDataItem() : LocalizationDetailsAdapterItem()

}