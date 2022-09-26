package com.nikhil.currencyconversion.ui.adapter

import com.nikhil.currencyconversion.R
import com.nikhil.currencyconversion.base.BaseRecyclerViewAdapter
import com.nikhil.currencyconversion.data.model.ConvertModel
import com.nikhil.currencyconversion.databinding.LayoutRecordBinding

class CurrencyAdapter : BaseRecyclerViewAdapter<ConvertModel, LayoutRecordBinding>() {

    override fun getLayout() = R.layout.layout_record

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<LayoutRecordBinding>,
        position: Int
    ) {
        holder.binding.txtDate.text = items[position].date
        holder.binding.txtFrom.text = items[position].from + " TO "+items[position].to
        holder.binding.txtResult.text = items[position].currency

    }
}
