package com.nikhil.currencyconversion.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikhil.currencyconversion.data.model.*
import com.nikhil.currencyconversion.domain.repository.CurrencyAppDao
import com.nikhil.currencyconversion.domain.use_cases.GetConversionCurrency
import com.nikhil.currencyconversion.domain.use_cases.GetCurrencySymbol
import com.nikhil.currencyconversion.domain.use_cases.GetOtherCountries
import com.nikhil.currencyconversion.util.Resource
import com.nikhil.currencyconversion.util.hasInternetConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val getCurrencySymbol: GetCurrencySymbol,
    private val getConversionCurrency: GetConversionCurrency,
    private val getOtherCountries: GetOtherCountries,
    private val currencyAppDao: CurrencyAppDao,
    @ApplicationContext private val context: Context
) : ViewModel() {


    var symbol: String? = ""
    val symbolData: MutableLiveData<Resource<List<Symbols>>> = MutableLiveData()
    val convertResult: MutableLiveData<Resource<ConvertResponse>> = MutableLiveData()

    val insertValue: MutableLiveData<Resource<Boolean>> = MutableLiveData()
    val list: MutableLiveData<Resource<List<ConvertModel>>> = MutableLiveData()
    val otherCurrencies: MutableLiveData<Resource<List<Rates>>> = MutableLiveData()
//
//
    init {
        fetchSymbol()
    }

    //
    fun fetchSymbol() {
        getCurrencySymbol().onEach {
            try {
                if (hasInternetConnection(context)) {
                    when (it) {
                        is Resource.Loading -> {
                            symbolData.postValue(Resource.Loading(""))
                        }
                        is Resource.Success -> {
                            symbolData.postValue(Resource.Success(it.data))
                        }
                        is Resource.Error -> {
                            symbolData.postValue(Resource.Error("Network Failure " + it.message))
                        }
                    }
                } else
                    symbolData.postValue(Resource.Error("No Internet Connection"))
            } catch (ex: Exception) {
                when (ex) {
                    is IOException -> symbolData.postValue(Resource.Error("Network Failure " + ex.localizedMessage))
                    else -> symbolData.postValue(Resource.Error("Conversion Error"))
                }
            }
        }.launchIn(viewModelScope)
    }
//

    fun conversionByCountryId(from: String, to: String, amount: String) {
        getConversionCurrency(from, to, amount).onEach {
            try {
                if (hasInternetConnection(context)) {
                    when (it) {
                        is Resource.Loading -> {
                            convertResult.postValue(Resource.Loading(""))
                        }
                        is Resource.Success -> {
                            convertResult.postValue(Resource.Success(it.data))
                        }
                        is Resource.Error -> {
                            convertResult.postValue(Resource.Error("Network Failure " + it.message))
                        }
                    }
                } else
                    convertResult.postValue(Resource.Error("No Internet Connection"))
            } catch (ex: Exception) {
                when (ex) {
                    is IOException -> convertResult.postValue(Resource.Error("Network Failure " + ex.localizedMessage))
                    else -> convertResult.postValue(Resource.Error("Conversion Error"))
                }
            }
        }.launchIn(viewModelScope)
    }
//
//
fun insertCurrency(convertModel: ConvertModel) {
    insertValue.postValue(Resource.Loading(""))
    viewModelScope.launch {
        try {
            currencyAppDao.insertQuery(convertModel)
            // insertValue.postValue(Resource.Success(true))
        } catch (e: Exception) {
            // insertValue.postValue(Resource.Error("Exception Thrown"))
        }
    }
}

    fun getAllList() {
        list.postValue(Resource.Loading(""))
        viewModelScope.launch {
            try {
                val response = currencyAppDao.getAllQueryDteWise()
                list.postValue(Resource.Success(response))
            } catch (e: Exception) {
                list.postValue(Resource.Error("Exception Thrown"))
            }
        }
    }
//
    fun getOtherCurrencies(baseCurrency:String) {
    getOtherCountries(baseCurrency).onEach {
        try {
            if (hasInternetConnection(context)) {
                when (it) {
                    is Resource.Loading -> {
                        otherCurrencies.postValue(Resource.Loading(""))
                    }
                    is Resource.Success -> {
                        otherCurrencies.postValue(Resource.Success(it.data))
                    }
                    is Resource.Error -> {
                        otherCurrencies.postValue(Resource.Error("Network Failure " + it.message))
                    }
                }
            } else
                otherCurrencies.postValue(Resource.Error("No Internet Connection"))
        } catch (ex: Exception) {
            when (ex) {
                is IOException -> otherCurrencies.postValue(Resource.Error("Network Failure " + ex.localizedMessage))
                else -> otherCurrencies.postValue(Resource.Error("Conversion Error"))
            }
        }
    }.launchIn(viewModelScope)
    }

}