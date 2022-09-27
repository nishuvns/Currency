package com.nikhil.currencyconversion.data.model

import com.google.gson.JsonObject

data class OtherCurrencies(
    val base: String,
    val date: String,
    val rates: JsonObject,
    val success: Boolean,
    val timestamp: Int
)
{
    /*
   As Fixer API provides all country name and country price in JSON OBJECT
   RecycleView work on list

   API EXAMPLE
   {
  "base": "USD",
  "date": "2022-04-14",
  "rates": {
    "EUR": 0.813399,
    "GBP": 0.72007,
    "JPY": 107.346001
  },
  "success": true,
  "timestamp": 1519296206
  https://apilayer.com/marketplace/fixer-api#documentation-tab
}
    */
    fun getRates(): ArrayList<Rates> {
        var listCountry = ArrayList<Rates>()


        for (item in rates.keySet()) {
            listCountry.add(Rates(item, rates.get(item).asString))
        }
        return listCountry
    }
}