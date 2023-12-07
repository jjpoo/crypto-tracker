package com.nosova.simple.coins.crypto_tracker.common

fun String.toPrice(scale: Int): String {
    val value = this.toDoubleOrNull() ?: return this
    return String.format("%.${scale}f $", value)
}
