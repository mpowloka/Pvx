package com.mpowloka.domain.localizations

data class Localization(
    val id: Long,
    val name: String,
    val barcode: String,
    val quantityOfItems: Int,
    val quantityOfPickedItems: Int
)