package com.mpowloka.pvx.localizations.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mpowloka.pvx.R

class LocalizationsRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<LocalizationsAdapterItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {

        LOCALIZATION_TYPE -> LocalizationViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_localization, parent, false)
        )

        else -> {
            throw IllegalArgumentException("$TAG: Unexpected ViewHolder type: $viewType")
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = when (getItemViewType(position)) {

        LOCALIZATION_TYPE -> {

            val localizationHolder = holder as LocalizationViewHolder
            val localization = items[position] as LocalizationsAdapterItem.Localization

            localizationHolder.bind(localization.localizationName)
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

        private const val TAG = "LocalizationsRecyclerAdapter"

        private const val LOCALIZATION_TYPE = 1
    }
}