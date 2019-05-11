package com.mpowloka.pvx.localizations.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_localization.view.*

class LocalizationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(localizationName: String) = with(itemView) {
        localization_localization_tv.text = localizationName
    }

}