package com.mpowloka.pvx.screens.itemdetails.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mpowloka.pvx.R
import com.mpowloka.pvx.screens.common.LocalizationViewHolder

class ItemDetailsRecyclerAdapter(
    private val onLocalizationClickedListener: LocalizationViewHolder.OnLocalizationClickedListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<ItemDetailsAdapterItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {

        LOCALIZATION_TYPE -> LocalizationViewHolder(
            onLocalizationClickedListener,
            LayoutInflater.from(parent.context).inflate(R.layout.item_localization, parent, false)
        )

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

        else -> {
            throw IllegalArgumentException("$TAG: Unexpected ViewHolder class: ${holder::class.java.name}")
        }

    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return LOCALIZATION_TYPE
    }

    companion object {

        private const val TAG = "ItemDetailsRecyclerAdapter"

        private const val LOCALIZATION_TYPE = 1
    }
}