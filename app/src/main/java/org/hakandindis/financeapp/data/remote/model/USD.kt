package org.hakandindis.financeapp.data.remote.model


import com.google.gson.annotations.SerializedName

data class USD(
    @SerializedName("price") val price: Double?,
)