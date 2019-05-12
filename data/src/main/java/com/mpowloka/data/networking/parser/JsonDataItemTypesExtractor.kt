package com.mpowloka.data.networking.parser

import com.mpowloka.data.networking.model.ItemTypeModel
import com.mpowloka.data.networking.model.JsonDataModel
import javax.inject.Inject

class JsonDataItemTypesExtractor @Inject constructor(){

    @Suppress("UNCHECKED_CAST")
    fun extractItemTypes(dataModel: JsonDataModel): List<ItemTypeModel> {
        val itemTypesTopMap = dataModel.tables.find { it["n"]?.equals("ItemType") ?: false } ?: return emptyList()
        val itemTypesColumnsList = itemTypesTopMap["c"] as? List<*> ?: return emptyList()

        val itemTypeIdColumnIndex = itemTypesColumnsList.indexOf("ItemTypeId")
        val itemTypeNameColumnIndex = itemTypesColumnsList.indexOf("Name")
        val itemTypeBarcodeColumnIndex = itemTypesColumnsList.indexOf("Barcode")

        val valuesTopList = itemTypesTopMap["r"] as? List<Map<String, List<Any>>> ?: return emptyList()
        val listOfValuesLists = valuesTopList.map { it["v"] }

        return listOfValuesLists.map {
            ItemTypeModel(
                (it?.get(itemTypeIdColumnIndex) as Double).toLong(),
                it.get(itemTypeNameColumnIndex) as String,
                it.get(itemTypeBarcodeColumnIndex) as String

            )
        }
    }

}