package com.nosova.simple.coins.crypto_tracker.domain.repository

import androidx.paging.PagingData
import com.nosova.simple.coins.crypto_tracker.domain.model.Coin
import kotlinx.coroutines.flow.Flow

interface CoinsRepository {
    fun getCoins(): Flow<PagingData<Coin>>
}