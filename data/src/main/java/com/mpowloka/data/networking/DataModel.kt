package com.mpowloka.data.networking

import com.google.gson.annotations.SerializedName
import java.util.*

data class DataModel(
    @SerializedName("t")
    val tables: List<Map<String, Any>>
)
