package com.mpowloka.pvx.localizations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mpowloka.pvx.R
import com.mpowloka.pvx.base.BaseViewModelFragment
import com.mpowloka.pvx.localizations.list.LocalizationsRecyclerAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_localizations.*
import javax.inject.Inject

class LocalizationsFragment : BaseViewModelFragment<LocalizationsViewModel>() {

    @Inject
    lateinit var localizationsRecyclerAdapter: LocalizationsRecyclerAdapter

    override fun getViewModelClass() = LocalizationsViewModel::class.java

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_localizations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()

        observeLocalizationsList()
    }

    @Suppress("UnstableApiUsage")
    private fun observeLocalizationsList() {
        viewModel.localizations
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .`as`(autoDisposable())
            .subscribe { adapterItems ->
                localizationsRecyclerAdapter.items = adapterItems
                localizationsRecyclerAdapter.notifyDataSetChanged()
            }
    }

    private fun setupRecyclerView() {
        localizations_recycler.layoutManager = LinearLayoutManager(context)
        localizations_recycler.adapter = localizationsRecyclerAdapter
    }

    companion object {
        private const val TAG = "LocalizationsFragment"
    }

}