package com.nosova.simple.coins.crypto_tracker.domain.model

import android.os.Parcel
import android.os.Parcelable
import com.nosova.simple.coins.crypto_tracker.data.remote.model.CoinDto

data class Coin(
    val id: String,
    val symbol: String,
    val name: String,
    val iconUrl: String,
    val price: String
) : Parcelable {
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(symbol)
        parcel.writeString(name)
        parcel.writeString(iconUrl)
        parcel.writeString(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CoinDto> {
        override fun createFromParcel(parcel: Parcel): CoinDto {
            return CoinDto(
                parcel.readString()!!,
                parcel.readString()!!,
                parcel.readString()!!,
                parcel.readString()!!,
                parcel.readString()!!
            )
        }

        override fun newArray(size: Int): Array<CoinDto?> {
            return arrayOfNulls(size)
        }
    }
}