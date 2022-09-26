package com.nikhil.currencyconversion.data.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class ConvertResponse(
    val date: String,
    val info: Info,
    val query: Query,
    val result: Double,
    val success: Boolean
)
