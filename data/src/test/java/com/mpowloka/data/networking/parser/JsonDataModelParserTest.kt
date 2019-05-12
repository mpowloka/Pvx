package com.mpowloka.data.networking.parser

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class JsonDataModelParserTest {

    private lateinit var SUT: JsonDataModelParser

    private lateinit var jsonDataHoldersExtractorMock: JsonDataHoldersExtractor
    private lateinit var jsonDataItemTypeHoldersExtractorMock: JsonDataItemTypeHoldersExtractor
    private lateinit var jsonDataItemTypesExtractorMock: JsonDataItemTypesExtractor

    @Before
    fun setUp() {
        mockExtractors()
        SUT = JsonDataModelParser(
            jsonDataHoldersExtractorMock,
            jsonDataItemTypeHoldersExtractorMock,
            jsonDataItemTypesExtractorMock
        )
    }

    @Test
    fun parseJsonDataModel_dataPassedToItemTypesExtractor() {
        SUT.parseJsonDataModel(JSON_DATA_MODEL)

        verify(jsonDataItemTypesExtractorMock, times(1)).extractItemTypes(JSON_DATA_MODEL)
    }

    @Test
    fun parseJsonDataModel_dataPassedToHoldersExtractor() {
        SUT.parseJsonDataModel(JSON_DATA_MODEL)

        verify(jsonDataHoldersExtractorMock, times(1)).extractHolders(JSON_DATA_MODEL)
    }

    @Test
    fun parseJsonDataModel_dataPassedToItemTypeHoldersExtractor() {
        SUT.parseJsonDataModel(JSON_DATA_MODEL)

        verify(jsonDataItemTypeHoldersExtractorMock, times(1)).extractItemTypeHolders(JSON_DATA_MODEL)
    }

    @Test
    fun parseJsonDataModel_dataFromHoldersExtractorReturned() {
        val result = SUT.parseJsonDataModel(JSON_DATA_MODEL)

        assertEquals(
            HOLDERS,
            result.holders
        )
    }

    @Test
    fun parseJsonDataModel_dataFromItemTypeHoldersExtractorReturned() {
        val result = SUT.parseJsonDataModel(JSON_DATA_MODEL)

        assertEquals(
            ITEM_TYPE_HOLDERS,
            result.itemTypeHolders
        )
    }

    @Test
    fun parseJsonDataModel_dataFromItemTypesExtractorReturned() {
        val result = SUT.parseJsonDataModel(JSON_DATA_MODEL)

        assertEquals(
            ITEM_TYPES,
            result.itemTypes
        )
    }

    private fun mockExtractors() {
        jsonDataHoldersExtractorMock = mock()
        jsonDataItemTypeHoldersExtractorMock = mock()
        jsonDataItemTypesExtractorMock = mock()

        whenever(jsonDataHoldersExtractorMock.extractHolders(JSON_DATA_MODEL)).thenReturn(
            HOLDERS
        )
        whenever(jsonDataItemTypeHoldersExtractorMock.extractItemTypeHolders(JSON_DATA_MODEL)).thenReturn(
            ITEM_TYPE_HOLDERS
        )
        whenever(jsonDataItemTypesExtractorMock.extractItemTypes(JSON_DATA_MODEL)).thenReturn(
            ITEM_TYPES
        )
    }


}