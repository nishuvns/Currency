package com.nikhil.currencyconversion.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nikhil.currencyconversion.data.model.ConvertModel
import com.nikhil.currencyconversion.data.model.ConvertResponse
import com.nikhil.currencyconversion.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [ConvertModel::class], version = 1)
abstract class CurrencyAppDatabase : RoomDatabase() {

    abstract fun getCurrencyDatabase(): CurrencyAppDao

    class Callback @Inject constructor(
        private val database: Provider<CurrencyAppDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback()
}