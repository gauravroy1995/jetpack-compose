package com.example.compose.network


import retrofit2.http.GET


interface AmphibianApiService {
    @GET("amphibians")
    suspend fun getAmphibians(): List<AmphibiansDataClass>
}