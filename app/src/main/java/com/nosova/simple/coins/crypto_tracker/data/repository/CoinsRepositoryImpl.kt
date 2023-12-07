package com.nosova.simple.coins.crypto_tracker.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.nosova.simple.coins.crypto_tracker.common.Constants
import com.nosova.simple.coins.crypto_tracker.common.mapper.toDomain
import com.nosova.simple.coins.crypto_tracker.data.local.CoinDatabase
import com.nosova.simple.coins.crypto_tracker.data.remote.mediator.CoinsRemoteMediator
import com.nosova.simple.coins.crypto_tracker.data.remote.service.CoinRankingApi
import com.nosova.simple.coins.crypto_tracker.domain.model.Coin
import com.nosova.simple.coins.crypto_tracker.domain.repository.CoinsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class CoinsRepositoryImpl
@Inject constructor(
    private val coinRankingApi: CoinRankingApi,
    private val database: CoinDatabase
) : CoinsRepository {

    override fun getCoins(): Flow<PagingData<Coin>> {
        return Pager(
            config = PagingConfig(
                Constants.PAGE_SIZE,
                prefetchDistance = 1
            ),
            remoteMediator = CoinsRemoteMediator(coinRankingApi, database.coinDao),
            pagingSourceFactory = {
                database.coinDao.getPagingSource()
            }
        ).flow.map { pagedData ->
            pagedData.map { coinEntity ->
                coinEntity.toDomain()
            }
        }
    }
}