package com.mpowloka.data

import com.mpowloka.data.networking.PvxApi
import org.junit.Before
import org.junit.Test

class PvxApiTest {

    /**
     * Please note these are not unit test, since they make use of an actual API.
     * Purpose of these is to lookup API result and to see if it behaves as expected.
     */

    private lateinit var SUT: PvxApi

    @Before
    fun setUp() {
        SUT = PvxApi.newInstance()
    }

    @Test
    fun downloadData_dataRetrieved() {
        val result = SUT.downloadData()

        result.test().assertValue {
            it.tables.isNotEmpty()
        }
    }
}