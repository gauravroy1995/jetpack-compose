package com.example.compose.network


import retrofit2.http.GET


interface AmphibianApiService {
    @GET("images/search?limit=10")
    suspend fun getAmphibians(): List<AmphibiansDataClass>
}