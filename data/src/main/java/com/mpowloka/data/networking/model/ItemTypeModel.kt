package com.mpowloka.data.networking.model

import com.mpowloka.domain.items.Item

data class ItemTypeModel(
    val itemTypeId: Long,
    val name: String,
    val barcode: String
) {

    fun toItem() = Item(itemTypeId, name, barcode)

    companion object {

        val NO_ITEM = ItemTypeModel(
            -1,
            "",
            ""
        )

    }

}