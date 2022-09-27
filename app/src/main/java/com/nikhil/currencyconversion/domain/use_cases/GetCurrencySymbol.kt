package com.nikhil.currencyconversion.domain.use_cases

import com.nikhil.currencyconversion.data.model.Symbols
import com.nikhil.currencyconversion.domain.repository.CurrencyRepository
import com.nikhil.currencyconversion.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCurrencySymbol @Inject constructor(private val currencyRepository:CurrencyRepository) {
    operator fun invoke(): Flow<Resource<List<Symbols>>> = flow {
        emit(Resource.Loading(""))
        try{
            emit(Resource.Success(currencyRepository.fetchSymbol()))

        }catch (e:Exception){
            emit(Resource.Error(e.message))
        }

    }
}