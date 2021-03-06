package com.mpowloka.pvx.screens.localizations

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mpowloka.domain.localizations.Localization
import com.mpowloka.pvx.R
import com.mpowloka.pvx.base.BaseViewModelFragment
import com.mpowloka.pvx.main.ActionBarConfiguration
import com.mpowloka.pvx.screens.common.LocalizationViewHolder
import com.mpowloka.pvx.screens.localizations.list.LocalizationsRecyclerAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_localizations.*
import javax.inject.Inject

class LocalizationsFragment : BaseViewModelFragment<LocalizationsViewModel>(),
    LocalizationViewHolder.OnLocalizationClickedListener {

    @Inject
    lateinit var localizationsRecyclerAdapter: LocalizationsRecyclerAdapter

    override fun onLocalizationClicked(localization: Localization) {
        findNavController().navigate(
            LocalizationsFragmentDirections.toLocalizationDetails(
                localization.id
            )
        )
    }

    override fun getViewModelClass() = LocalizationsViewModel::class.java

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_localizations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        observeTypedPhrase()

        setupActionBar(
            ActionBarConfiguration(
                getString(R.string.pvx),
                getString(R.string.all_localizations)
            )
        )
    }

    override fun onResume() {
        super.onResume()

        observeLocalizationsList()
    }

    @Suppress("UnstableApiUsage")
    private fun observeLocalizationsList() {
        viewModel.adapterItems
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .`as`(autoDisposable())
            .subscribe(
                { adapterItems ->
                    localizationsRecyclerAdapter.items = adapterItems
                    localizationsRecyclerAdapter.notifyDataSetChanged()
                },
                { exception ->
                    exception.printStackTrace()
                }
            )
    }

    private fun observeTypedPhrase() {
        localizations_filter_et.addTextChangedListener(object: TextWatcher {

            override fun afterTextChanged(s: Editable) {
                viewModel.filterPhraseTyped(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }

        })
    }

    private fun setupRecyclerView() {
        localizations_recycler.layoutManager = LinearLayoutManager(context)
        localizations_recycler.adapter = localizationsRecyclerAdapter
    }

    companion object {
        private const val TAG = "LocalizationsFragment"
    }

}