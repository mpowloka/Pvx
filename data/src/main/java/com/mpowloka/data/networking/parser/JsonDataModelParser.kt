package com.mpowloka.data.networking.parser

import com.mpowloka.data.networking.model.HolderModel
import com.mpowloka.data.networking.model.ItemTypeHolderModel
import com.mpowloka.data.networking.model.ItemTypeModel
import com.mpowloka.data.networking.model.JsonDataModel
import javax.inject.Inject

class JsonDataModelParser @Inject constructor(
    private val jsonDataHoldersExtractor: JsonDataHoldersExtractor,
    private val jsonDataItemTypeHoldersExtractor: JsonDataItemTypeHoldersExtractor,
    private val jsonDataItemTypesExtractor: JsonDataItemTypesExtractor
) {

    fun parseJsonDataModel(dataModel: JsonDataModel): ParsedData {

        return ParsedData(
            jsonDataHoldersExtractor.extractHolders(dataModel),
            jsonDataItemTypeHoldersExtractor.extractItemTypeHolders(dataModel),
            jsonDataItemTypesExtractor.extractItemTypes(dataModel)
        )

    }


    data class ParsedData(
        val holders: List<HolderModel>,
        val itemTypeHolders: List<ItemTypeHolderModel>,
        val itemTypes: List<ItemTypeModel>
    )
}