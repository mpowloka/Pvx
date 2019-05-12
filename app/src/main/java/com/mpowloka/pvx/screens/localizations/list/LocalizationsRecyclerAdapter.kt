package com.mpowloka.pvx.screens.localizations.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mpowloka.pvx.R
import com.mpowloka.pvx.screens.common.LocalizationViewHolder
import com.mpowloka.pvx.screens.common.NoDataViewHolder

class LocalizationsRecyclerAdapter(
    private val onLocalizationClickedListener: LocalizationViewHolder.OnLocalizationClickedListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<LocalizationsAdapterItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when (viewType) {

        LOCALIZATION_TYPE -> LocalizationViewHolder(
            onLocalizationClickedListener,
            LayoutInflater.from(parent.context).inflate(R.layout.item_localization, parent, false)
        )

        NO_DATA_TYPE -> NoDataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_no_data, parent, false)
        )

        else -> {
            throw IllegalArgumentException("$TAG: Unexpected ViewHolder type: $viewType")
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = when (getItemViewType(position)) {

        LOCALIZATION_TYPE -> {

            val localizationHolder = holder as LocalizationViewHolder
            val localization = items[position] as LocalizationsAdapterItem.LocalizationItem

            localizationHolder.bind(localization.localization)
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

            is LocalizationsAdapterItem.LocalizationItem -> LOCALIZATION_TYPE

            is LocalizationsAdapterItem.NoDataItem -> NO_DATA_TYPE
        }
    }

    companion object {

        private const val TAG = "LocalizationsRecyclerAdapter"

        private const val LOCALIZATION_TYPE = 1

        private const val NO_DATA_TYPE = 2
    }
}