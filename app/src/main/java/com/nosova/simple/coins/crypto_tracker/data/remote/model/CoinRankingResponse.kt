package com.nosova.simple.coins.crypto_tracker.data.remote.model
import com.google.gson.annotations.SerializedName

data class CoinRankingResponse(
    val data: Data
)

data class Data(
    val coins: List<CoinDto>
)

data class CoinDto(
    @SerializedName("uuid")
    val id: String,
    val symbol: String,
    val name: String,
    val iconUrl: String,
    val price: String
)