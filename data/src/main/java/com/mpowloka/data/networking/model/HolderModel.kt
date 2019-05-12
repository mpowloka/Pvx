package com.mpowloka.data.networking.model

import com.mpowloka.domain.localizations.Localization

data class HolderModel(
    val holderId: Long,
    val name: String,
    val barcode: String,
    val quantityOfItems: Int,
    val quantityOfPickedItems: Int
) {

    fun toLocalization() = Localization(
        holderId,
        name,
        barcode,
        quantityOfItems,
        quantityOfPickedItems
    )

}