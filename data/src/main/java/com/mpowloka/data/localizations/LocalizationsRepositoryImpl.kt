package com.mpowloka.data.localizations

import com.mpowloka.data.networking.cache.HoldersCache
import com.mpowloka.data.networking.cache.ItemTypeHoldersCache
import com.mpowloka.domain.localizations.Localization
import com.mpowloka.domain.localizations.LocalizationsRepository
import io.reactivex.Flowable
import io.reactivex.rxkotlin.Flowables
import javax.inject.Inject

class LocalizationsRepositoryImpl @Inject constructor(
    private val holdersCache: HoldersCache,
    private val itemTypeHoldersCache: ItemTypeHoldersCache
) : LocalizationsRepository {

    override fun getLocalizationForId(id: Long): Flowable<Localization> {
        return holdersCache.getHolderForId(id).map {
            it.toLocalization()
        }
    }

    override fun getAllLocalizations(): Flowable<List<Localization>> {

        return holdersCache.getHolders().map { models ->
            models.map { it.toLocalization() }
        }

    }

    override fun getLocalizationsWithItem(itemId: Long): Flowable<List<Localization>> {
        return Flowables.combineLatest(
            holdersCache.getHolders(),
            itemTypeHoldersCache.getItemHolderTypesForItemId(itemId)
        ) { holderModels, itemTypeHoldersCache ->
            val localizationIdsWithItem = itemTypeHoldersCache.map { it.holderId }
            holderModels.filter { localizationIdsWithItem.contains(it.holderId) }.map { it.toLocalization() }
        }
    }
}