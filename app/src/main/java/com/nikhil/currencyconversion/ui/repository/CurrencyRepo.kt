package com.nikhil.currencyconversion.ui.repository

import com.nikhil.currencyconversion.data.CurrencyService
import com.nikhil.currencyconversion.data.model.ConvertResponse
import com.nikhil.currencyconversion.data.model.OtherCurrencies
import com.nikhil.currencyconversion.data.model.SymbolResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import retrofit2.Response

@Singleton
class CurrencyRepo @Inject constructor(
    private val currencyAppService: CurrencyService
) {

    suspend fun fetchSymbol(): Response<SymbolResponse> = withContext(
        Dispatchers.IO) {
        val symbol = currencyAppService.getSymbols()
        symbol
    }

    suspend fun getAmount(from:String, to:String ,amount:String ): Response<ConvertResponse> = withContext(
        Dispatchers.IO) {
        val convertResponse = currencyAppService.getAmount(from,to,amount)
        convertResponse
    }

    suspend fun getOtherCurrencies(baseCurrency:String ): Response<OtherCurrencies> = withContext(
        Dispatchers.IO) {
        val convertResponse = currencyAppService.getOtherCurrencies(baseCurrency)
        convertResponse
    }
}