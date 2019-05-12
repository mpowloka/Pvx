package com.mpowloka.data.networking

import com.mpowloka.data.networking.cache.HoldersCache
import com.mpowloka.data.networking.cache.ItemTypeHoldersCache
import com.mpowloka.data.networking.cache.ItemTypesCache
import com.mpowloka.data.networking.model.JsonDataModel
import com.mpowloka.data.networking.parser.JsonDataModelParser
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DataDownloader @Inject constructor(
    private val api: PvxApi,
    private val jsonDataModelParser: JsonDataModelParser,
    private val holdersCache: HoldersCache,
    private val itemTypesCache: ItemTypesCache,
    private val itemTypeHoldersCache: ItemTypeHoldersCache
){

    private var currentDisposable: Disposable? = null

    fun downloadData() {

        currentDisposable?.dispose()

        currentDisposable = api.downloadData()
            .firstOrError()
            .subscribeOn(Schedulers.io())
            .subscribe(
                { jsonDataModel ->
                    saveDataInCache(jsonDataModel)
                },
                { exception ->
                    println("Failed to download data")
                    exception.printStackTrace()
                }
            )
    }

    fun saveDataInCache(jsonDataModel: JsonDataModel) {
        val parsedData = jsonDataModelParser.parseJsonDataModel(jsonDataModel)

        holdersCache.addHolders(parsedData.holders)
        itemTypesCache.addItemTypes(parsedData.itemTypes)
        itemTypeHoldersCache.addItemTypeHolders(parsedData.itemTypeHolders)
    }

}