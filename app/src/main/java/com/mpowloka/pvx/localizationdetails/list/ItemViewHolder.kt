package com.mpowloka.pvx.localizationdetails.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mpowloka.domain.items.Item
import com.mpowloka.pvx.R
import kotlinx.android.synthetic.main.item_item.view.*

class ItemViewHolder(
    private val onItemClickedListener: OnItemClickedListener,
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: Item) = with(itemView) {
        displayItemData(item)

        item_card.setOnClickListener {
            onItemClickedListener.onItemClicked(item)
        }
    }

    private fun displayItemData(item: Item) = with(itemView) {
        item_name_tv.text = item.name
        item_barcode_tv.text = item.barcode

        item_total_items_count.text = resources.getString(
            R.string.item_total_count_placeholder,
            5
        )
    }

    interface OnItemClickedListener {

        fun onItemClicked(item: Item)

    }

}