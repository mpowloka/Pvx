package com.mpowloka.pvx.localizationdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mpowloka.domain.items.Item
import com.mpowloka.pvx.R
import com.mpowloka.pvx.base.BaseViewModelFragment
import com.mpowloka.pvx.localizationdetails.list.ItemViewHolder
import com.mpowloka.pvx.localizationdetails.list.LocalizationDetailsRecyclerAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_localization_details.*
import javax.inject.Inject

class LocalizationDetailsFragment : BaseViewModelFragment<LocalizationDetailsViewModel>(),
    ItemViewHolder.OnItemClickedListener {

    @Inject
    lateinit var localizationDetailsRecyclerAdapter: LocalizationDetailsRecyclerAdapter

    private var localizationId = -1L

    override fun getViewModelClass() = LocalizationDetailsViewModel::class.java

    override fun onItemClicked(item: Item) {
        findNavController().navigate(
            LocalizationDetailsFragmentDirections.toItemDetails(item.id)
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_localization_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        extractArguments()

        setupRecycler()
    }

    override fun onResume() {
        super.onResume()

        displayLocalizationItems()
    }

    private fun setupRecycler() {
        localization_details_recycler.layoutManager = LinearLayoutManager(context)
        localization_details_recycler.adapter = localizationDetailsRecyclerAdapter
    }

    @Suppress("UnstableApiUsage")
    private fun displayLocalizationItems() {
        viewModel
            .getAdapterItemsForLocalization(localizationId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .`as`(autoDisposable())
            .subscribe(
                { adapterItems ->
                    localizationDetailsRecyclerAdapter.items = adapterItems
                    localizationDetailsRecyclerAdapter.notifyDataSetChanged()
                },
                { exception ->
                    exception.printStackTrace()
                }
            )
    }

    private fun extractArguments() {
        localizationId = LocalizationDetailsFragmentArgs
            .fromBundle(arguments ?: return)
            .localizationId
    }
}