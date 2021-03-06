package com.mpowloka.pvx.screens.itemdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mpowloka.domain.localizations.Localization
import com.mpowloka.pvx.R
import com.mpowloka.pvx.base.BaseViewModelFragment
import com.mpowloka.pvx.main.ActionBarConfiguration
import com.mpowloka.pvx.main.UpButtonConfiguration
import com.mpowloka.pvx.screens.common.LocalizationViewHolder
import com.mpowloka.pvx.screens.itemdetails.list.ItemDetailsRecyclerAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_item_details.*
import javax.inject.Inject

class ItemDetailsFragment : BaseViewModelFragment<ItemDetailsViewModel>(),
    LocalizationViewHolder.OnLocalizationClickedListener {

    @Inject
    lateinit var itemDetailsRecyclerAdapter: ItemDetailsRecyclerAdapter

    private var itemId = -1L

    override fun getViewModelClass() = ItemDetailsViewModel::class.java

    override fun onLocalizationClicked(localization: Localization) {
        findNavController().navigate(
            ItemDetailsFragmentDirections.toLocalizationDetails(localization.id)
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_item_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        extractArguments()

        setupRecycler()


    }

    override fun onResume() {
        super.onResume()

        displayItems()

        displayItemDetails()
    }

    @Suppress("UnstableApiUsage")
    private fun displayItemDetails() {
        viewModel.getItemForItemId(itemId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .`as`(autoDisposable())
            .subscribe(
                { item ->
                    setupActionBar(ActionBarConfiguration(
                        getString(R.string.item_name_placeholder, item.name),
                        item.barcode,
                        UpButtonConfiguration(
                            true,
                            R.drawable.ic_home,
                            { findNavController().popBackStack(R.id.localizationsFrgment, false) }
                        )
                    ))

                },
                { exception ->
                    exception.printStackTrace()
                }
            )
    }

    @Suppress("UnstableApiUsage")
    private fun displayItems() {
        viewModel.getAdapterItemsForItem(itemId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .`as`(autoDisposable())
            .subscribe(
                { adapterItems ->
                    itemDetailsRecyclerAdapter.items = adapterItems
                    itemDetailsRecyclerAdapter.notifyDataSetChanged()
                },
                { exception ->
                    exception.printStackTrace()
                }
            )
    }

    private fun setupRecycler() {
        item_details_recycler.layoutManager = LinearLayoutManager(context)
        item_details_recycler.adapter = itemDetailsRecyclerAdapter
    }

    private fun extractArguments() {
        itemId = ItemDetailsFragmentArgs
            .fromBundle(arguments ?: return)
            .itemId
    }

}