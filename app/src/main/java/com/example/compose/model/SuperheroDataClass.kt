package com.example.compose.model

import androidx.annotation.DrawableRes

data class SuperheroDataClass(
    val title: String,
    val description: String,
   @DrawableRes val imageUrl: Int
)
