package org.hakandindis.financeapp.data.remote.model


import com.google.gson.annotations.SerializedName

data class CoinResponse(
    @SerializedName("data")
    val `data`: List<Coin?>?,
    @SerializedName("status")
    val status: Status?
)