package com.nikhil.currencyconversion.di

import android.app.Application
import androidx.room.Room
import com.nikhil.currencyconversion.data.local.CurrencyAppDao
import com.nikhil.currencyconversion.data.local.CurrencyAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application, callback: CurrencyAppDatabase.Callback): CurrencyAppDatabase{
        return Room.databaseBuilder(application, CurrencyAppDatabase::class.java, "currency_database")
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .build()
    }

    @Provides
    fun provideMovieAppDao(db: CurrencyAppDatabase): CurrencyAppDao{
        return db.getCurrencyDatabase()
    }
}

