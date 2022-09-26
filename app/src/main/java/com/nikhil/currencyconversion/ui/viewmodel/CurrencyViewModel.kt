package com.nikhil.currencyconversion.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikhil.currencyconversion.data.local.CurrencyAppDao
import com.nikhil.currencyconversion.data.model.ConvertModel
import com.nikhil.currencyconversion.data.model.ConvertResponse
import com.nikhil.currencyconversion.data.model.OtherCurrencies
import com.nikhil.currencyconversion.data.model.SymbolResponse
import com.nikhil.currencyconversion.ui.repository.CurrencyRepo
import com.nikhil.currencyconversion.util.Resource
import com.nikhil.currencyconversion.util.hasInternetConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val currencyRepo: CurrencyRepo, private val currencyAppDao: CurrencyAppDao,
    @ApplicationContext private val context: Context
) : ViewModel() {
    var symbol: String? = ""
    val symbolData: MutableLiveData<Resource<SymbolResponse>> = MutableLiveData()
    val convertResult: MutableLiveData<Resource<ConvertResponse>> = MutableLiveData()
    val insertValue: MutableLiveData<Resource<Boolean>> = MutableLiveData()
    val list: MutableLiveData<Resource<List<ConvertModel>>> = MutableLiveData()
    val otherCurrencies: MutableLiveData<Resource<OtherCurrencies>> = MutableLiveData()


    init {
        fetchSymbol()
    }

    fun fetchSymbol() {
        symbolData.postValue(Resource.Loading())
        viewModelScope.launch {
            try {
                if (hasInternetConnection(context)) {
                    val response = currencyRepo.fetchSymbol()
                    symbolData.postValue(Resource.Success(response.body()!!))
                } else
                    symbolData.postValue(Resource.Error("No Internet Connection"))
            } catch (ex: Exception) {
                when (ex) {
                    is IOException -> symbolData.postValue(Resource.Error("Network Failure " + ex.localizedMessage))
                    else -> symbolData.postValue(Resource.Error("Conversion Error"))
                }
            }
        }
    }

    fun conversionByCountryId(from: String, to: String, amount: String) {
        convertResult.postValue(Resource.Loading())
        viewModelScope.launch {
            try {
                if (hasInternetConnection(context)) {
                    val response = currencyRepo.getAmount(from, to, amount)
                    convertResult.postValue(Resource.Success(response.body()!!))
                } else
                    convertResult.postValue(Resource.Error("No Internet Connection"))
            } catch (ex: Exception) {
                when (ex) {
                    is IOException -> convertResult.postValue(Resource.Error("Network Failure " + ex.localizedMessage))
                    else -> convertResult.postValue(Resource.Error("Conversion Error"))
                }
            }
        }
    }


    fun insertCurrency(convertModel: ConvertModel) {
        insertValue.postValue(Resource.Loading())
        viewModelScope.launch {
            try {
                currencyAppDao.insertQuery(convertModel)
                insertValue.postValue(Resource.Success(true))
            } catch (e: Exception) {
                insertValue.postValue(Resource.Error("Exception Thrown"))
            }
        }
    }

    fun getAllList() {
        list.postValue(Resource.Loading())
        viewModelScope.launch {
            try {
                val response = currencyAppDao.getAllQueryDteWise()
                list.postValue(Resource.Success(response))
            } catch (e: Exception) {
                list.postValue(Resource.Error("Exception Thrown"))
            }
        }
    }

    fun getOtherCurrencies(baseCurrency:String) {
        otherCurrencies.postValue(Resource.Loading())
        viewModelScope.launch {
            try {
                if (hasInternetConnection(context)) {
                    val response = currencyRepo.getOtherCurrencies(baseCurrency=baseCurrency)
                    otherCurrencies.postValue(Resource.Success(response.body()!!))
                } else
                    otherCurrencies.postValue(Resource.Error("No Internet Connection"))
            } catch (ex: Exception) {
                when (ex) {
                    is IOException -> otherCurrencies.postValue(Resource.Error("Network Failure " + ex.localizedMessage))
                    else -> otherCurrencies.postValue(Resource.Error("Conversion Error"))
                }
            }
        }
    }

}