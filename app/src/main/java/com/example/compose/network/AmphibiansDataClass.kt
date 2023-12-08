package com.example.compose.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AmphibiansDataClass(
    val kind: String,
    val totalItems: Int,
    val items: List<ItemClass>,
//    @SerialName(value = "img_src")
//    val imgSrc: String
)


@Serializable
data class ItemClass(
    val id: String
)


@Serializable
data class EachBookClass(
    val id: String,
    val volumeInfo: VolumeInfo,
    val saleInfo: SaleInfo
)


@Serializable
data class SaleInfo(
    val listPrice: ListPrice? = null
)

@Serializable
data class ListPrice(
    val amount: Double? = 0.0
)

@Serializable
data class VolumeInfo(
    val title: String,
    val authors: List<String>,
    val imageLinks: ImageLinks? = null,
    val publishedDate: String? = "Unknown"
)

@Serializable
data class ImageLinks(
    val thumbnail: String?=""
)