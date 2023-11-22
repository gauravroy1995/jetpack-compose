package com.example.compose.data

import com.example.compose.network.AmphibianApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val amphibianPhotoRepository: AmphibianPhotosRepository
//    val catPhotoRepository: AmphibianPhotosRepository
}

class DefaultAppContainer : AppContainer {
    private val KEY = "live_bVAxiiLMYwxMXHWB1lbw1To5HyKuiO4KwyiyaNpch3zvI0QtyCHJsyn8lrcQNFv2"
    private val baseUrl =
        "https://api.thecatapi.com/v1/"

    private val amphibianClient = OkHttpClient.Builder().apply {
        addInterceptor(Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("x-api-key", KEY)
                .build()
            chain.proceed(request)
        })
    }.build()

    /**
     * both work JSONand GSON for converter
     */
    private val retrofit: Retrofit = Retrofit.Builder()
//        .addConverterFactory(Json{ignoreUnknownKeys=true}.asConverterFactory("application/json".toMediaType()))
        .addConverterFactory(GsonConverterFactory.create())
        .client(amphibianClient)
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: AmphibianApiService by lazy {
        retrofit.create(AmphibianApiService::class.java)
    }

    override val amphibianPhotoRepository: AmphibianPhotosRepository by lazy {
        NetworkAmphibiansPhotosRepository(retrofitService)
    }


//    private val catUrl =
//        "https://api.thecatapi.com/v1/images/search?size=med&mime_types=jpg&format=json&has_breeds=true&order=RANDOM&page=0&limit=1"
//
//    private val catRetrofit: Retrofit = Retrofit.Builder()
//        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
//        .baseUrl(catUrl)
//        .build()
//
//    private val catRetrofitService: AmphibianApiService by lazy {
//        retrofit.create(AmphibianApiService::class.java)
//    }
//
//    override val catPhotoRepository: AmphibianPhotosRepository by lazy {
//        NetworkAmphibiansPhotosRepository(retrofitService)
//    }



}