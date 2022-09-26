package com.nikhil.currencyconversion.data.model

data class Query(
    val amount: Int,
    val from: String,
    val to: String
)