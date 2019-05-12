package com.mpowloka.data.networking.model

data class ItemTypeHolderModel(
    val itemTypeHolderId: Long,
    val itemTypeId: Long,
    val holderId: Long,
    val quantity: Int,
    val pickQuantity: Int
)