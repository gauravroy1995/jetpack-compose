package com.example.compose.network


import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface AmphibianApiService {
    @GET("books/v1/volumes?q=onepiece")
    suspend fun getAmphibians(): AmphibiansDataClass

    @GET("books/v1/volumes/{volId}")
    suspend fun getEachBook(@Path("volId") volId:String): EachBookClass

}