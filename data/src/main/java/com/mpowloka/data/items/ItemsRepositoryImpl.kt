package com.mpowloka.data.items

import com.mpowloka.domain.items.Item
import com.mpowloka.domain.items.ItemsRepository
import com.mpowloka.domain.localizations.Localization
import io.reactivex.Flowable
import javax.inject.Inject

class ItemsRepositoryImpl @Inject constructor() : ItemsRepository {

    override fun getItemForId(id: Long): Flowable<Item> {
        return Flowable.just(Item(1, "Item1", "1-ASDF-GAT"))
    }

    override fun getItemsInLocalization(localizationId: Long): Flowable<List<Item>> {
        return Flowable.just(listOf(
            Item(1, "Item1", "1-ASDF-GAT"),
            Item(2, "Item2", "2-ASDF-GAT"),
            Item(3, "Item3", "3-ASDF-GAT")
        ))
    }

}