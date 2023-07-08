package org.hakandindis.financeapp.data.remote.service

import org.hakandindis.financeapp.data.remote.model.CoinResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CoinService {

    @GET("/v1/cryptocurrency/listings/latest")
    suspend fun getLatestCoins(
        @Header("X-CMC_PRO_API_KEY") apiKey: String,
        @Query("limit") limit: String
    ): CoinResponse

}