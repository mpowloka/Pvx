package com.mpowloka.pvx.screens.itemdetails.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mpowloka.pvx.R
import com.mpowloka.pvx.screens.common.LocalizationViewHolder
import com.mpowloka.pvx.screens.common.NoDataViewHolder

class ItemDetailsRecyclerAdapter(
    private val onLocalizationClickedListener: LocalizationViewHolder.OnLocalizationClickedListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<ItemDetailsAdapterItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {

        LOCALIZATION_TYPE -> LocalizationViewHolder(
            onLocalizationClickedListener,
            LayoutInflater.from(parent.context).inflate(R.layout.item_localization, parent, false)
        )

        NO_DATA_TYPE -> {
            NoDataViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_no_data, parent, false)
            )
        }

        else -> {
            throw IllegalArgumentException("$TAG: Unexpected ViewHolder type: $viewType")
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = when (getItemViewType(position)) {

        LOCALIZATION_TYPE -> {

            val localizationHolder = holder as LocalizationViewHolder
            val itemLocalization = items[position] as ItemDetailsAdapterItem.ItemLocalization

            localizationHolder.bind(itemLocalization.localization)
        }

        NO_DATA_TYPE -> {

        }

        else -> {
            throw IllegalArgumentException("$TAG: Unexpected ViewHolder class: ${holder::class.java.name}")
        }

    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {

            is ItemDetailsAdapterItem.ItemLocalization -> LOCALIZATION_TYPE

            is ItemDetailsAdapterItem.NoDataItem -> NO_DATA_TYPE
        }
    }

    companion object {

        private const val TAG = "ItemDetailsRecyclerAdapter"

        private const val LOCALIZATION_TYPE = 1

        private const val NO_DATA_TYPE = 2
    }
}