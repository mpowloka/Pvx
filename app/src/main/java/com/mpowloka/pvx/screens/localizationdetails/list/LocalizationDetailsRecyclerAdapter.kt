package com.mpowloka.pvx.screens.localizationdetails.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mpowloka.pvx.R
import com.mpowloka.pvx.screens.common.NoDataViewHolder

class LocalizationDetailsRecyclerAdapter(
    private val onItemClickedListener: ItemViewHolder.OnItemClickedListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<LocalizationDetailsAdapterItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {

        ITEM_TYPE -> ItemViewHolder(
            onItemClickedListener,
            LayoutInflater.from(parent.context).inflate(R.layout.item_item, parent, false)
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

        ITEM_TYPE -> {

            val itemHolder = holder as ItemViewHolder
            val itemWithTotalCount = items[position] as LocalizationDetailsAdapterItem.ItemItem

            itemHolder.bind(itemWithTotalCount.item)
        }

        NO_DATA_TYPE -> {

        }

        else -> {
            throw IllegalArgumentException("$TAG: Unexpected ViewHolder class: ${holder::class.java.name}")
        }

    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return when(items[position]) {

            is LocalizationDetailsAdapterItem.ItemItem -> ITEM_TYPE

            is LocalizationDetailsAdapterItem.NoDataItem -> NO_DATA_TYPE
        }
    }

    companion object {

        private const val TAG = "LocalizationDetailsRecyclerAdapter"

        private const val ITEM_TYPE = 1

        private const val NO_DATA_TYPE = 2
    }
}