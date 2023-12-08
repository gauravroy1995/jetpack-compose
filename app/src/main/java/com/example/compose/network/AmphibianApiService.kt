package com.example.compose.network


import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface AmphibianApiService {
    @GET("books/v1/volumes")
    suspend fun getAmphibians(
        @Query("q") query: String = "onepiece",
        @Query("startIndex") startIndex: Int = 0,
        @Query("maxResults") maxResults: Int = 10
    ): AmphibiansDataClass

    @GET("books/v1/volumes/{volId}")
    suspend fun getEachBook(@Path("volId") volId:String): EachBookClass

}