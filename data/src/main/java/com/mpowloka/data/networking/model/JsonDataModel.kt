package com.mpowloka.data.networking.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class JsonDataModel(
    @SerializedName("t")
    val tables: List<Map<String, Any>>
)
