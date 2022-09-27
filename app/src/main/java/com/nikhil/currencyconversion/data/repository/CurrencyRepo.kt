package com.nikhil.currencyconversion.data.repository

import com.nikhil.currencyconversion.data.model.*
import com.nikhil.currencyconversion.data.network.CurrencyService
import com.nikhil.currencyconversion.domain.repository.CurrencyRepository
import com.nikhil.currencyconversion.util.SafeApiRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import retrofit2.Response

@Singleton
class CurrencyRepo @Inject constructor(
    private val currencyAppService: CurrencyService
) : CurrencyRepository, SafeApiRequest() {
    override suspend fun fetchSymbol(): List<Symbols> {
        val response = safeApiRequest { currencyAppService.getSymbols() }
        return response.getSymbols()
    }

    override suspend fun getAmount(from: String, to: String, amount: String): ConvertResponse {
        val convertResponse = safeApiRequest { currencyAppService.getAmount(from, to, amount) }
        return convertResponse
    }

    override suspend fun getOtherCurrencies(baseCurrency: String): List<Rates> {
        val convertResponse = safeApiRequest { currencyAppService.getOtherCurrencies(baseCurrency) }
        return convertResponse.getRates()
    }
}