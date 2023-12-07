package com.nosova.simple.coins.crypto_tracker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CoinEntity::class],
    version = 1
)
abstract class CoinDatabase : RoomDatabase() {

    abstract val coinDao: CoinRankingDao
}