package org.hakandindis.financeapp.data.remote.model


import com.google.gson.annotations.SerializedName

data class Quote(
    @SerializedName("USD")
    val uSD: USD?
)