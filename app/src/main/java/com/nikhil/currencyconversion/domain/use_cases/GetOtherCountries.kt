package com.nikhil.currencyconversion.domain.use_cases

import com.nikhil.currencyconversion.data.model.Rates
import com.nikhil.currencyconversion.domain.repository.CurrencyRepository
import com.nikhil.currencyconversion.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetOtherCountries @Inject constructor(private val currencyRepository: CurrencyRepository) {
    operator fun invoke(base:String): Flow<Resource<List<Rates>>> = flow {
        emit(Resource.Loading(""))
        try{
            emit(Resource.Success(currencyRepository.getOtherCurrencies(base)))

        }catch (e:Exception){
            emit(Resource.Error(e.message))
        }

    }
}