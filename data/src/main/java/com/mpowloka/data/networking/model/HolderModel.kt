package com.mpowloka.data.networking.model

data class HolderModel(
    val holderId: Long,
    val name: String,
    val barcode: String,
    val quantityOfItems: Int,
    val quantityOfPickedItems: Int
)