package com.mpowloka.pvx.screens.localizationdetails.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mpowloka.domain.items.Item
import com.mpowloka.domain.items.ItemWithTotalCount
import com.mpowloka.pvx.R
import kotlinx.android.synthetic.main.item_item.view.*

class ItemViewHolder(
    private val onItemClickedListener: OnItemClickedListener,
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(itemWithTotalCount: ItemWithTotalCount) = with(itemView) {
        displayItemData(itemWithTotalCount)

        item_card.setOnClickListener {
            onItemClickedListener.onItemClicked(itemWithTotalCount.item)
        }
    }

    private fun displayItemData(itemWithTotalCount: ItemWithTotalCount) = with(itemView) {
        item_name_tv.text = itemWithTotalCount.item.name
        item_barcode_tv.text = itemWithTotalCount.item.barcode

        item_total_items_count.text = resources.getString(
            R.string.item_total_count_placeholder,
            itemWithTotalCount.totalCount
        )
    }

    interface OnItemClickedListener {

        fun onItemClicked(item: Item)

    }

}