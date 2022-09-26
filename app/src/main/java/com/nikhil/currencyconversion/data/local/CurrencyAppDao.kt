package com.nikhil.currencyconversion.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.nikhil.currencyconversion.data.model.ConvertModel

@Dao
interface CurrencyAppDao {
    @Query("SELECT * FROM convertmodel  ORDER BY id DESC")
    suspend  fun getAllQueryDteWise(): List<ConvertModel>

    @Insert
    suspend fun insertQuery(convertmodel: ConvertModel)
}