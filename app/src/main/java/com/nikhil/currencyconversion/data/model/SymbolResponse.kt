package com.nikhil.currencyconversion.data.model

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class SymbolResponse(val success: Boolean, val symbols: JsonObject)
{
    /*
    As Fixer API provides all country name in JSON OBJECT
    Spinner we need list
     */
    fun getSymbols(): ArrayList<Symbols> {
        var listCountry = ArrayList<Symbols>()
        for (item in symbols.keySet()) {
            listCountry.add(Symbols(item, symbols.get(item).asString))
        }
        return listCountry
    }
}