package com.mpowloka.data.networking.parser

import com.mpowloka.data.networking.model.HolderModel
import com.mpowloka.data.networking.model.JsonDataModel

class JsonDataHoldersExtractor {

    @Suppress("UNCHECKED_CAST")
    fun extractHolders(dataModel: JsonDataModel): List<HolderModel> {
        val holdersTopMap = dataModel.tables.find { it["n"]?.equals("Holder") ?: false } ?: return emptyList()
        val holdersColumnsList = holdersTopMap["c"] as? List<*> ?: return emptyList()

        val holderIdColumnIndex = holdersColumnsList.indexOf("HolderId")
        val holderNameColumnIndex = holdersColumnsList.indexOf("Name")
        val holderBarcodeColumnIndex = holdersColumnsList.indexOf("Barcode")
        val holderQuantityColumnIndex = holdersColumnsList.indexOf("QuantityOfItems")
        val holderPickedQuantityColumnIndex = holdersColumnsList.indexOf("QuantityOfPickedItems")

        val valuesTopList = holdersTopMap["r"] as? List<Map<String, List<Any>>> ?: return emptyList()
        val listOfValuesLists = valuesTopList.map { it["v"] }

        return listOfValuesLists.map {
            HolderModel(
                (it?.get(holderIdColumnIndex) as Double).toLong(),
                it.get(holderNameColumnIndex) as String,
                it.get(holderBarcodeColumnIndex) as String,
                (it.get(holderQuantityColumnIndex) as Double).toInt(),
                (it.get(holderPickedQuantityColumnIndex) as Double).toInt()

            )
        }
    }

}