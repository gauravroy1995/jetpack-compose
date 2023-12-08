package com.example.compose.data

import android.content.Context
import com.example.compose.database.InventoryDatabase
import com.example.compose.network.AmphibianApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit



interface AppContainer {
    val amphibianPhotoRepository: AmphibianPhotosRepository
    val booksDataRepository: BooksDataRepository
}

class DefaultAppContainer(context: Context) : AppContainer {

    private val baseUrl =
        "https://www.googleapis.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json {
            ignoreUnknownKeys = true
        }.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: AmphibianApiService by lazy {
        retrofit.create(AmphibianApiService::class.java)
    }

    override val amphibianPhotoRepository: AmphibianPhotosRepository by lazy {
        NetworkAmphibiansPhotosRepository(retrofitService)
    }

    override val booksDataRepository: BooksDataRepository by lazy {
        BooksDataRepository(InventoryDatabase.getDatabase(context).bookDao())
    }

}