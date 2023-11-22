package com.example.compose.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AmphibiansDataClass(
    @SerialName("id")
    val id: String,
    @SerialName("url")
    val url: String,
    @SerialName("width")
    val width: Int,
    @SerialName("height")
    val height: Int,
    @SerialName("breeds")
    val breeds: List<Breed>, // You might need to define a Breed data class if it contains more information
    @SerialName("favourite")
    val favourite: Favourite?=null // You might need to define a Favourite data class if it contains more information
)

@Serializable
data class Favourite(
    val name:String
)

@Serializable
data class Breed(
    val name:String
)