package com.mpowloka.data.networking.model

data class ItemTypeHolderModel(
    val itemTypeHolderId: Long,
    val itemTypeId: Long,
    val holderI: Long,
    val quantity: Int,
    val pickQuantity: Int
)