package com.mpowloka.pvx.screens.localizationdetails

import androidx.lifecycle.ViewModel
import com.mpowloka.domain.items.ItemsRepository
import com.mpowloka.domain.localizations.Localization
import com.mpowloka.domain.localizations.LocalizationsRepository
import com.mpowloka.pvx.screens.localizationdetails.list.LocalizationDetailsAdapterItem
import io.reactivex.Flowable
import javax.inject.Inject

class LocalizationDetailsViewModel @Inject constructor(
    private val itemsRepository: ItemsRepository,
    private val localizationsRepository: LocalizationsRepository
) : ViewModel() {

    private val localizationsItemsCacheMap
            = mutableMapOf<Long, Flowable<List<LocalizationDetailsAdapterItem>>>()

    private val localizationIsLocalizationsCacheMap
            = mutableMapOf<Long, Flowable<Localization>>()

    fun getAdapterItemsForLocalization(localizationId: Long): Flowable<List<LocalizationDetailsAdapterItem>> {
        val cachedValue = localizationsItemsCacheMap.get(localizationId)
        if(cachedValue != null) {
            return cachedValue
        }

        return itemsRepository.getItemsInLocalization(localizationId).map { items ->


            if(items.isEmpty()) {
                listOf(LocalizationDetailsAdapterItem.NoDataItem())
            } else {
                items.map { LocalizationDetailsAdapterItem.ItemItem(it) as LocalizationDetailsAdapterItem }
            }


        }.also {
            localizationsItemsCacheMap[localizationId] = it
        }
    }

    fun getLocalizationForId(localizationId: Long): Flowable<Localization> {
        val cachedValue = localizationIsLocalizationsCacheMap[localizationId]
        if(cachedValue != null) {
            return cachedValue
        }

        return localizationsRepository.getLocalizationForId(localizationId).also {
            localizationIsLocalizationsCacheMap[localizationId] = it
        }
    }

}