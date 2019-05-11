package com.mpowloka.pvx.localizationdetails

import androidx.lifecycle.ViewModel
import com.mpowloka.domain.items.ItemsRepository
import com.mpowloka.pvx.localizationdetails.list.LocalizationDetailsAdapterItem
import io.reactivex.Flowable
import javax.inject.Inject

class LocalizationDetailsViewModel @Inject constructor(
    private val itemsRepository: ItemsRepository
) : ViewModel() {

    private val localizationsItemsCacheMap
            = mutableMapOf<Long, Flowable<List<LocalizationDetailsAdapterItem>>>()

    fun getItemsForLocalizationId(localizationId: Long): Flowable<List<LocalizationDetailsAdapterItem>> {
        val cachedValue = localizationsItemsCacheMap.get(localizationId)
        if(cachedValue != null) {
            return cachedValue
        }

        return itemsRepository.getItemsInLocalization(localizationId).map { items ->
            items.map { LocalizationDetailsAdapterItem.ItemItem(it) as LocalizationDetailsAdapterItem }
        }.also {
            localizationsItemsCacheMap[localizationId] = it
        }
    }

}