package com.mpowloka.pvx.screens.common

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mpowloka.domain.localizations.Localization
import com.mpowloka.pvx.R
import kotlinx.android.synthetic.main.item_localization.view.*

class LocalizationViewHolder(
    private val onLocalizationClickedListener: OnLocalizationClickedListener,
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(localization: Localization) = with(itemView) {
        displayLocalizationData(localization)

        localization_card.setOnClickListener {
            onLocalizationClickedListener.onLocalizationClicked(localization)
        }
    }

    private fun displayLocalizationData(localization: Localization) = with(itemView) {
        localization_name_tv.text = localization.name
        localization_barcode_tv.text = localization.barcode
        localization_items_count_tv.text = resources.getString(
            R.string.items_count_placeholder,
            localization.quantityOfPickedItems,
            localization.quantityOfItems
        )
    }

    interface OnLocalizationClickedListener {

        fun onLocalizationClicked(localization: Localization)

    }

}