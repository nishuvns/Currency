package com.nikhil.currencyconversion.ui.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView
import com.nikhil.currencyconversion.R

import com.nikhil.currencyconversion.data.model.Symbols


//class SymbolAdapter(context: Context,  symbolList: ArrayList<Symbols>) :
//    ArrayAdapter<Symbols>(context, 0, symbolList) {
//    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
//        return super.getDropDownView(position, convertView, parent)
//    }
//
//    private fun initView(position: Int, convertView: View, parent: ViewGroup): View {
//        // It is used to set our custom view.
//        var convertView: View = convertView
//        if (convertView == null) {
//            convertView =
//                LayoutInflater.from(context).inflate(R.layout.spinner, parent, false)
//        }
//        val textViewName: TextView = convertView.findViewById(R.id.text_view)
//        val currentItem: Symbols? = getItem(position)
//
//        // It is used the name to the TextView when the
//        // current item is not null.
//        if (currentItem != null) {
//            textViewName.setText(currentItem.countryId)
//        }
//        return convertView
//    }
//}

class SymbolAdapter(val context: Context, var dataSource: ArrayList<Symbols>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View
        val vh: ItemHolder
        if (convertView == null) {
            view = inflater.inflate(R.layout.spinner, parent, false)
            vh = ItemHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ItemHolder
        }
        vh.label.text = dataSource.get(position).countryId

        return view
    }

    override fun getItem(position: Int): Any? {
        return dataSource[position];
    }

    override fun getCount(): Int {
        return dataSource.size;
    }

    override fun getItemId(position: Int): Long {
        return position.toLong();
    }

    private class ItemHolder(row: View?) {
        val label: TextView

        init {
            label = row?.findViewById(R.id.text_view) as TextView
        }
    }

}