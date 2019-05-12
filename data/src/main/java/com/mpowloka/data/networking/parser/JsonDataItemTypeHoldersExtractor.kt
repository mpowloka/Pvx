package com.mpowloka.data.networking.parser

import com.mpowloka.data.networking.model.ItemTypeHolderModel
import com.mpowloka.data.networking.model.JsonDataModel
import javax.inject.Inject

class JsonDataItemTypeHoldersExtractor @Inject constructor() {

    @Suppress("UNCHECKED_CAST")
    fun extractItemTypeHolders(dataModel: JsonDataModel): List<ItemTypeHolderModel> {
        val itemTypeHoldersTopMap =
            dataModel.tables.find { it["n"]?.equals("ItemTypeHolder") ?: false } ?: return emptyList()
        val itemTypeHoldersColumnsList = itemTypeHoldersTopMap["c"] as? List<*> ?: return emptyList()

        val itemTypeHolderIdColumnIndex = itemTypeHoldersColumnsList.indexOf("ItemTypeHolderId")
        val itemTypeIdColumnIndex = itemTypeHoldersColumnsList.indexOf("ItemTypeId")
        val holderIdColumnIndex = itemTypeHoldersColumnsList.indexOf("HolderId")
        val quantityColumnIndex = itemTypeHoldersColumnsList.indexOf("Quantity")
        val pickedQuantityColumnIndex = itemTypeHoldersColumnsList.indexOf("PickQuantity")

        val valuesTopList = itemTypeHoldersTopMap["r"] as? List<Map<String, List<Any>>> ?: return emptyList()
        val listOfValuesLists = valuesTopList.map { it["v"] }

        return listOfValuesLists.map {
            ItemTypeHolderModel(
                (it?.get(itemTypeHolderIdColumnIndex) as Double).toLong(),
                (it.get(itemTypeIdColumnIndex) as Double).toLong(),
                (it.get(holderIdColumnIndex) as Double).toLong(),
                (it.get(quantityColumnIndex) as Double).toInt(),
                (it.get(pickedQuantityColumnIndex) as Double).toInt()

            )
        }
    }
}
