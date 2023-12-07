package com.nosova.simple.coins.crypto_tracker.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CoinEntity(
    @PrimaryKey
    val id: String,
    val symbol: String,
    val name: String,
    @ColumnInfo(name = "icon_url")
    val iconUrl: String,
    val price: String
)
