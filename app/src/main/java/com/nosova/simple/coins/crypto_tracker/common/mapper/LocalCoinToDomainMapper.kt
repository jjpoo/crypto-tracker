package com.nosova.simple.coins.crypto_tracker.common.mapper

import com.nosova.simple.coins.crypto_tracker.data.local.CoinEntity
import com.nosova.simple.coins.crypto_tracker.domain.model.Coin

fun CoinEntity.toDomain() = Coin(
    id = id,
    symbol = symbol,
    name = name,
    iconUrl = iconUrl,
    price = price
)