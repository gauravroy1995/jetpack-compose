package com.example.compose.data

import com.example.compose.network.AmphibianApiService
import com.example.compose.network.AmphibiansDataClass


interface AmphibianPhotosRepository {
    suspend fun getAmphibians(): List<AmphibiansDataClass>
}

class NetworkAmphibiansPhotosRepository(
    private val amphibianApiService: AmphibianApiService
) : AmphibianPhotosRepository {
    override suspend fun getAmphibians(): List<AmphibiansDataClass> {
        return amphibianApiService.getAmphibians()
    }
}
