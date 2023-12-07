package com.nosova.simple.coins.crypto_tracker.data.remote.service

import com.nosova.simple.coins.crypto_tracker.data.remote.model.CoinRankingResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinRankingApi {

    @GET("/v2/coins")
    suspend fun getCoins(
        @Query("offset") after: Int,
        @Query("limit") count: Int
    ): CoinRankingResponse
}