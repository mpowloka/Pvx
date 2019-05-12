package com.mpowloka.data.networking.parser

import com.mpowloka.data.networking.model.HolderModel
import com.mpowloka.data.networking.model.ItemTypeHolderModel
import com.mpowloka.data.networking.model.ItemTypeModel
import com.mpowloka.data.networking.model.JsonDataModel

internal val HOLDERS = listOf(
    HolderModel(
        729240L,
        "RSW-A11",
        "BIGRA",
        2,
        3
    ),
    HolderModel(
        730711L,
        "UNPICK-A4",
        "U4",
        0,
        2
    )
)

internal val ITEM_TYPE_HOLDERS = listOf(
    ItemTypeHolderModel(
        1629634,
        69038,
        730711,
        0,
        2
    ),
    ItemTypeHolderModel(
        1629632,
        69037,
        729240,
        2,
        0
    )
)

internal val ITEM_TYPES = listOf(
    ItemTypeModel(
        18,
        "Essential",
        "M0475"
    ),
    ItemTypeModel(
        19,
        "Essential",
        "M0475"
    )
)

internal val JSON_DATA_MODEL = JsonDataModel(
    listOf(
        mapOf(
            Pair("n", "Holder"),
            Pair(
                "c", listOf(
                    "HolderId",
                    "HolderTypeId",
                    "Barcode",
                    "Name",
                    "QuantityOfItems",
                    "QuantityOfPickedItems",
                    "SiteId",
                    "Sequence",
                    "StorageTypeI",
                    "isLocked",
                    "LocationUseTypeId",
                    "ParentHolderId",
                    "ParentHolderName",
                    "ZoneId",
                    "LocationGroupId"
                )
            ),
            Pair(
                "r", listOf(
                    mapOf(
                        Pair(
                            "v", listOf(
                                729240.0,
                                1.0,
                                "BIGRA",
                                "RSW-A11",
                                2.0,
                                3.0,
                                6.0,
                                "004.011",
                                3.0,
                                0.0,
                                2.0,
                                null,
                                null,
                                null,
                                null
                            )
                        )
                    ),
                    mapOf(
                        Pair(
                            "v", listOf(
                                730711.0,
                                1.0,
                                "U4",
                                "UNPICK-A4",
                                0.0,
                                2.0,
                                6.0,
                                "006.004",
                                null,
                                0.0,
                                2.0,
                                null,
                                null,
                                null,
                                null
                            )
                        )
                    )
                )
            )
        ),
        mapOf(
            Pair("n", "ItemTypeHolder"),
            Pair(
                "c", listOf(
                    "ItemTypeHolderId",
                    "ItemTypeId",
                    "HolderId",
                    "Quantity",
                    "PickQuantity"
                )
            ),
            Pair(
                "r", listOf(
                    mapOf(
                        Pair(
                            "v", listOf(
                                1629634.0,
                                69038.0,
                                730711.0,
                                0.0,
                                2.0
                            )
                        )
                    ),
                    mapOf(
                        Pair(
                            "v", listOf(
                                1629632.0,
                                69037.0,
                                729240.0,
                                2.0,
                                0.0
                            )
                        )
                    )
                )
            )
        ),
        mapOf(
            Pair("n", "ItemType"),
            Pair(
                "c", listOf(
                    "ItemTypeId",
                    "ItemCode",
                    "Name",
                    "Description",
                    "Barcode",
                    "DefaultSuppliersPartNumber",
                    "HasSerialNumbers",
                    "UseManufacturersSerialNumber",
                    "Weight",
                    "WeightUnitId",
                    "Height",
                    "Width",
                    "Depth",
                    "Attribute1",
                    "Attribute2",
                    "Attribute3",
                    "Attribute4",
                    "Attribute5",
                    "Attribute6",
                    "Attribute7",
                    "Attribute8",
                    "Attribute9",
                    "Attribute10",
                    "Attribute11",
                    "Attribute12",
                    "Attribute13",
                    "Attribute14",
                    "Attribute15"
                )
            ),
            Pair(
                "r", listOf(
                    mapOf(
                        Pair(
                            "v", listOf(
                                18.0,
                                "EHB",
                                "Essential",
                                "",
                                "M0475",
                                "",
                                0.0,
                                0.0,
                                0.6500,
                                11.0,
                                0.0,
                                0.0,
                                0.0,
                                "",
                                "",
                                "",
                                "",
                                "",
                                "",
                                "",
                                "",
                                "",
                                "",
                                "",
                                "",
                                "",
                                "",
                                ""
                            )
                        )
                    ),
                    mapOf(
                        Pair(
                            "v", listOf(
                                19.0,
                                "EB",
                                "Essential",
                                "",
                                "M0475",
                                "",
                                0.0,
                                0.0,
                                0.6500,
                                11.0,
                                0.1,
                                0.0,
                                0.0,
                                "",
                                "",
                                "",
                                "",
                                "",
                                "",
                                "",
                                "",
                                "",
                                "",
                                "",
                                "",
                                "",
                                "",
                                ""
                            )
                        )
                    )
                )
            )
        )
    )
)

