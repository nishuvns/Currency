package com.nikhil.currencyconversion.domain.use_cases

import com.nikhil.currencyconversion.data.model.ConvertResponse
import com.nikhil.currencyconversion.data.model.Symbols
import com.nikhil.currencyconversion.domain.repository.CurrencyRepository
import com.nikhil.currencyconversion.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetConversionCurrency @Inject constructor(private val currencyRepository: CurrencyRepository) {
    operator fun invoke(from:String, to:String ,amount:String ): Flow<Resource<ConvertResponse>> = flow {
        emit(Resource.Loading(""))
        try{
            emit(Resource.Success(currencyRepository.getAmount(from, to, amount)))

        }catch (e:Exception){
            emit(Resource.Error(e.message))
        }

    }
}