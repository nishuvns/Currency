package com.nikhil.currencyconversion.domain.repository

import com.nikhil.currencyconversion.data.model.*
import retrofit2.Response

interface CurrencyRepository
{
    suspend fun fetchSymbol():  List<Symbols>
    suspend fun getAmount(from:String, to:String ,amount:String ): ConvertResponse
    suspend fun getOtherCurrencies(baseCurrency:String ): List<Rates>
}