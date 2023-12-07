package com.nosova.simple.coins.crypto_tracker.common.mapper

import com.nosova.simple.coins.crypto_tracker.data.local.CoinEntity
import com.nosova.simple.coins.crypto_tracker.data.remote.model.CoinDto

fun CoinDto.toLocal() = CoinEntity(
    id = id,
    symbol = symbol,
    name = name,
    iconUrl = iconUrl,
    price = price
)
