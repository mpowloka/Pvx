package com.mpowloka.data.networking.parser

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class JsonDataItemTypesExtractorTest {

    private lateinit var SUT: JsonDataItemTypesExtractor

    @Before
    fun setUp() {
        SUT = JsonDataItemTypesExtractor()
    }

    @Test
    fun extractItemTypes_itemTypesReturned() {
        val result = SUT.extractItemTypes(JSON_DATA_MODEL)

        assertEquals(
            ITEM_TYPES,
            result
        )
    }
}