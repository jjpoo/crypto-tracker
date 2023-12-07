package com.nosova.simple.coins.crypto_tracker.data.di

import com.nosova.simple.coins.crypto_tracker.data.repository.CoinsRepositoryImpl
import com.nosova.simple.coins.crypto_tracker.domain.repository.CoinsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindCoinsRepository(
        coinsRepositoryImpl: CoinsRepositoryImpl
    ): CoinsRepository
}