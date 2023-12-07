package com.nosova.simple.coins.crypto_tracker.data.remote.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.nosova.simple.coins.crypto_tracker.common.mapper.toLocal
import com.nosova.simple.coins.crypto_tracker.data.local.CoinEntity
import com.nosova.simple.coins.crypto_tracker.data.local.CoinRankingDao
import com.nosova.simple.coins.crypto_tracker.data.remote.service.CoinRankingApi

@OptIn(ExperimentalPagingApi::class)
class CoinsRemoteMediator(
    private val api: CoinRankingApi,
    private val dao: CoinRankingDao
) : RemoteMediator<Int, CoinEntity>() {

    private var pageIndex = 0
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CoinEntity>
    ): MediatorResult {

        val pageIndex =
            getPageIndex(loadType) ?: return MediatorResult.Success(endOfPaginationReached = true)

        val limit = state.config.pageSize
        val offset = pageIndex * limit

        return try {
            val apiResponse = api.getCoins(offset, limit)
                .data
                .coins
                .map { remoteCoin ->
                    remoteCoin.toLocal()
                }

            if (loadType == LoadType.REFRESH) {
                dao.clearAll()
            } else {
                dao.saveCoins(apiResponse)
            }

            MediatorResult.Success(
                endOfPaginationReached = apiResponse.size < limit
            )

        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private fun getPageIndex(loadType: LoadType): Int? {
        pageIndex = when (loadType) {
            LoadType.REFRESH -> 0
            LoadType.PREPEND -> return null
            LoadType.APPEND -> ++pageIndex
        }
        return pageIndex
    }
}