package com.mpowloka.pvx.localizations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mpowloka.pvx.R
import com.mpowloka.pvx.localizations.list.LocalizationsAdapterItem
import com.mpowloka.pvx.localizations.list.LocalizationsRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_localizations.*

class LocalizationsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_localizations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val adapter = LocalizationsRecyclerAdapter()

        localizations_recycler.layoutManager = LinearLayoutManager(context)
        localizations_recycler.adapter = adapter

        adapter.items = listOf(
            LocalizationsAdapterItem.Localization("London"),
            LocalizationsAdapterItem.Localization("Tokyo"),
            LocalizationsAdapterItem.Localization("Cheese"),
            LocalizationsAdapterItem.Localization("Malbork")
        )
    }

}