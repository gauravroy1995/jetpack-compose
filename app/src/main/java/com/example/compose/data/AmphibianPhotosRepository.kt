package com.example.compose.data

import com.example.compose.network.AmphibianApiService
import com.example.compose.network.AmphibiansDataClass
import com.example.compose.network.EachBookClass


interface AmphibianPhotosRepository {
    suspend fun getAmphibians(): AmphibiansDataClass
    suspend fun getEachBook(volId:String): EachBookClass
}

class NetworkAmphibiansPhotosRepository(
    private val amphibianApiService: AmphibianApiService
) : AmphibianPhotosRepository {
    override suspend fun getAmphibians(): AmphibiansDataClass {
        return amphibianApiService.getAmphibians()
    }
    override suspend fun getEachBook(volId: String): EachBookClass {
        return amphibianApiService.getEachBook(volId)
    }
}
