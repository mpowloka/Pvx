package com.mpowloka.data.networking.parser

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class JsonDataItemTypeHoldersExtractorTest {

    private lateinit var SUT: JsonDataItemTypeHoldersExtractor

    @Before
    fun setUp() {
        SUT = JsonDataItemTypeHoldersExtractor()
    }

    @Test
    fun extractItemTypeHolders_itemTypeHoldersReturned() {
        val result = SUT.extractItemTypeHolders(JSON_DATA_MODEL)

        assertEquals(
            ITEM_TYPE_HOLDERS,
            result
        )
    }
}