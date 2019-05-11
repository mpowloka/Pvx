package com.mpowloka.pvx.localizationdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mpowloka.pvx.R
import com.mpowloka.pvx.base.BaseViewModelFragment
import kotlinx.android.synthetic.main.fragment_localization_details.*

class LocalizationDetailsFragment : BaseViewModelFragment<LocalizationDetailsViewModel>() {

    override fun getViewModelClass() = LocalizationDetailsViewModel::class.java

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_localization_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val localizationId = LocalizationDetailsFragmentArgs
            .fromBundle(arguments ?: return)
            .localizationId

        localization_details_item_name.text = localizationId.toString()
    }
}