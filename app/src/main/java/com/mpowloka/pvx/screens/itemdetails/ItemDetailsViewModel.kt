package com.mpowloka.pvx.screens.itemdetails

import androidx.lifecycle.ViewModel
import com.mpowloka.domain.items.Item
import com.mpowloka.domain.items.ItemsRepository
import com.mpowloka.domain.localizations.LocalizationsRepository
import com.mpowloka.pvx.screens.itemdetails.list.ItemDetailsAdapterItem
import com.mpowloka.pvx.screens.localizationdetails.list.LocalizationDetailsAdapterItem
import io.reactivex.Flowable
import javax.inject.Inject

class ItemDetailsViewModel @Inject constructor(
    private val localizationsRepository: LocalizationsRepository,
    private val itemsRepository: ItemsRepository
) : ViewModel() {

    private val itemsAdapterItemsCacheMap = mutableMapOf<Long, Flowable<List<ItemDetailsAdapterItem>>>()
    private val itemIdsItemsCacheMap = mutableMapOf<Long, Flowable<Item>>()

    fun getAdapterItemsForItem(itemId: Long): Flowable<List<ItemDetailsAdapterItem>> {
        val cachedValue = itemsAdapterItemsCacheMap[itemId]
        if(cachedValue != null) {
            return cachedValue
        }

        return localizationsRepository.getLocalizationsWithItem(itemId)
            .map { items ->

                if(items.isEmpty()) {
                    listOf(ItemDetailsAdapterItem.NoDataItem())
                } else {
                    items.map { ItemDetailsAdapterItem.ItemLocalization(it) as ItemDetailsAdapterItem }
                }

            }.also {
                itemsAdapterItemsCacheMap[itemId] = it
            }
    }

    fun getItemForItemId(itemId: Long): Flowable<Item> {
        val cachedValue = itemIdsItemsCacheMap[itemId]
        if(cachedValue != null) {
            return cachedValue
        }

        return itemsRepository.getItemForId(itemId).also {
            itemIdsItemsCacheMap[itemId] = it
        }
    }



}