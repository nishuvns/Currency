package com.nikhil.currencyconversion.domain.di

import com.nikhil.currencyconversion.data.network.CurrencyService
import com.nikhil.currencyconversion.data.repository.CurrencyRepo
import com.nikhil.currencyconversion.domain.repository.CurrencyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {
    @Provides
    fun provideGetCurrencyRepo(apiService: CurrencyService):CurrencyRepository{
        return CurrencyRepo(currencyAppService = apiService)
    }
}