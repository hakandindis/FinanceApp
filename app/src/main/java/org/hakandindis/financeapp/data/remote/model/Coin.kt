package org.hakandindis.financeapp.data.remote.model


import com.google.gson.annotations.SerializedName

data class Coin(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("quote") val quote: Quote?,
    @SerializedName("symbol") val symbol: String?,
)