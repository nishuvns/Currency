package com.nikhil.currencyconversion.presentation.adapter

import com.nikhil.currencyconversion.R
import com.nikhil.currencyconversion.base.BaseRecyclerViewAdapter
import com.nikhil.currencyconversion.data.model.ConvertModel
import com.nikhil.currencyconversion.data.model.OtherCurrencies
import com.nikhil.currencyconversion.data.model.Rates
import com.nikhil.currencyconversion.databinding.LayoutRecordBinding
import com.nikhil.currencyconversion.databinding.SpinnerBinding

class OtherCurriencesAdapter  : BaseRecyclerViewAdapter<Rates, SpinnerBinding>() {

    override fun getLayout() = R.layout.spinner

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<SpinnerBinding>,
        position: Int
    ) {
        holder.binding.textView.text = items[position].countryName + " "+items[position].countryPrice


    }
}