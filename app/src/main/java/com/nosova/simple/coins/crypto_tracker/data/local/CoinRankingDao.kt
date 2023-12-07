package com.nosova.simple.coins.crypto_tracker.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CoinRankingDao {
    @Query("SELECT * FROM coinEntity")
    fun getPagingSource(): PagingSource<Int, CoinEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCoins(coins: List<CoinEntity>)

    @Query("DELETE FROM coinEntity")
    suspend fun clearAll()
}