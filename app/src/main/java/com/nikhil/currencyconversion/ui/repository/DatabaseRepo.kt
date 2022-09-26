package com.nikhil.currencyconversion.ui.repository

import androidx.annotation.WorkerThread
import com.nikhil.currencyconversion.data.local.CurrencyAppDao
import com.nikhil.currencyconversion.data.model.ConvertModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseRepo @Inject constructor( private val currencyAppDao: CurrencyAppDao) {

    @WorkerThread
    suspend fun getList(): List<ConvertModel> = withContext(
        Dispatchers.IO) {
        val getList = currencyAppDao.getAllQueryDteWise()
        getList
    }



    @WorkerThread
    suspend fun insert(data:ConvertModel)= withContext(
        Dispatchers.IO) {
       currencyAppDao.insertQuery(data)
    }

}