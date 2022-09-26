package com.nikhil.currencyconversion.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ConvertModel(
    val date: String?, val from: String?, val to: String?, val currency: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
