package com.example.geministarter.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.geministarter.BuildConfig
import com.example.geministarter.FlightApplication
import com.example.geministarter.SummarizeViewModel
import com.example.geministarter.history.HistoryViewModel
import com.example.geministarter.nutri.NutriViewModel
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.generationConfig

val GenerativeViewModelFactory = object : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(
        viewModelClass: Class<T>,
        extras: CreationExtras
    ): T {
        val config = generationConfig {
            temperature = 0.7f
        }

        return with(viewModelClass) {
            when {
                isAssignableFrom(SummarizeViewModel::class.java) -> {
                    // Initialize a GenerativeModel with the `gemini-pro` AI model
                    // for text generation
                    val generativeModel = GenerativeModel(
                        modelName = "gemini-pro",
                        apiKey = BuildConfig.apiKey,
                        generationConfig = config
                    )
                    SummarizeViewModel(generativeModel)
                }

                isAssignableFrom(NutriViewModel::class.java) -> {
                    // Initialize a GenerativeModel with the `gemini-pro` AI model
                    // for text generation
                    val generativeModel = GenerativeModel(
                        modelName = "gemini-pro-vision",
                        apiKey = BuildConfig.apiKey,
                        generationConfig = config
                    )
                    NutriViewModel(
                        generativeModel,
                        busRepository = extras.busApplication().container.favoritesRepository
                    )
                }

                isAssignableFrom(HistoryViewModel::class.java) -> {
                    HistoryViewModel(busRepository = extras.busApplication().container.favoritesRepository)
                }


                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${viewModelClass.name}")
            }
        } as T
    }
}

fun CreationExtras.busApplication(): FlightApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FlightApplication)