package com.mpowloka.pvx.screens.itemdetails

import androidx.lifecycle.ViewModel
import com.mpowloka.domain.localizations.LocalizationsRepository
import com.mpowloka.pvx.screens.itemdetails.list.ItemDetailsAdapterItem
import io.reactivex.Flowable
import javax.inject.Inject

class ItemDetailsViewModel @Inject constructor(
    private val localizationsRepository: LocalizationsRepository
) : ViewModel() {

    private val itemsAdapterItemsCacheMap = mutableMapOf<Long, Flowable<List<ItemDetailsAdapterItem>>>()

    fun getAdapterItemsForItem(itemId: Long): Flowable<List<ItemDetailsAdapterItem>> {
        val cachedValue = itemsAdapterItemsCacheMap[itemId]
        if(cachedValue != null) {
            return cachedValue
        }

        return localizationsRepository.getLocalizationsWithItem(itemId)
            .map { items ->
                items.map { ItemDetailsAdapterItem.ItemLocalization(it) as ItemDetailsAdapterItem }
            }.also {
                itemsAdapterItemsCacheMap[itemId] = it
            }
    }

}