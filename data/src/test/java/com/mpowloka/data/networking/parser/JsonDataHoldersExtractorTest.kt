package com.mpowloka.data.networking.parser

import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class JsonDataHoldersExtractorTest {

    private lateinit var SUT: JsonDataHoldersExtractor

    @Before
    fun setUp() {
        SUT = JsonDataHoldersExtractor()
    }

    @Test
    fun extractHolders_listOfHoldersReturned() {
        val holders = SUT.extractHolders(JSON_DATA_MODEL)

        Assert.assertEquals(
            HOLDERS,
            holders
        )
    }
}