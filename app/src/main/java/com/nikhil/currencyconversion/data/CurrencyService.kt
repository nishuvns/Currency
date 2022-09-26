package com.nikhil.currencyconversion.data

import com.nikhil.currencyconversion.data.model.ConvertResponse
import com.nikhil.currencyconversion.data.model.OtherCurrencies
import com.nikhil.currencyconversion.data.model.SymbolResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface CurrencyService {

    companion object {
        const val ENDPOINT = "https://api.apilayer.com/fixer/"
        const val APIKEY =  "apikey"
        const val KEY_VALUE = "hcmcsd94DX5ockD2DFswq6fIYB7Det6z"
    }
    @Headers( "$APIKEY: $KEY_VALUE")
    @GET("symbols")
    suspend fun getSymbols(): Response<SymbolResponse>

    @Headers("$APIKEY: $KEY_VALUE")
    @GET("convert?")
    suspend fun getAmount(
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("amount") amount: String,
    ): Response<ConvertResponse>

    @Headers("$APIKEY: $KEY_VALUE")
    @GET("latest?")
    suspend fun getOtherCurrencies(
        @Query("base") base: String,
    ): Response<OtherCurrencies>

}