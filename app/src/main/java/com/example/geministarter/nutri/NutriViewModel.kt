package com.example.geministarter.nutri

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geministarter.database.PhotoSearchEntity
import com.example.geministarter.repository.FavRepository
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NutriViewModel(
    private val generativeModel: GenerativeModel,
    private val busRepository: FavRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<NutriUiState> =
        MutableStateFlow(NutriUiState.Initial)
    val uiState: StateFlow<NutriUiState> =
        _uiState.asStateFlow()

    private val _recipesState: MutableStateFlow<RecipeUiState> =
        MutableStateFlow(RecipeUiState.Initial)
    val recipesState: StateFlow<RecipeUiState> =
        _recipesState.asStateFlow()

    fun reason(
        selectedImages: List<Bitmap>,
        uriImage: Uri
    ) {
        _uiState.value = NutriUiState.Loading
        val prompt = "Look at the image(s), and then can you just name the eatable item(s) also its nutritional values per 100gms, give some recipes with it in english"

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val inputContent = content {
                    for (bitmap in selectedImages) {
                        image(bitmap)
                    }
                    text(prompt)
                }

                var outputContent = ""

                generativeModel.generateContentStream(inputContent)
                    .collect { response ->
                        outputContent += response.text
                        _uiState.value = NutriUiState.Success(outputContent)
                    }

                busRepository.insertItem(PhotoSearchEntity(query = "what", uriString =  uriImage.toString(), answer =  outputContent))

            } catch (e: Exception) {
                _uiState.value = NutriUiState.Error(e.localizedMessage ?: "")
            }
        }
    }

    fun getRecipes(
        selectedImages: List<Bitmap>
    ) {
        val prompt = "Look at the image(s), and then can you just check eatable item(s)  and also give 5 website links for  recipes with that eatable, i just want the links to be as response, nothing else"
        _recipesState.value = RecipeUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val inputContent = content {
                    for (bitmap in selectedImages) {
                        image(bitmap)
                    }
                    text(prompt)
                }

                var outputContent = ""

                generativeModel.generateContentStream(inputContent)
                    .collect { response ->
                        outputContent += response.text

                    }
                _recipesState.value = RecipeUiState.Success(outputContent)
            } catch (e: Exception) {
                _recipesState.value = RecipeUiState.Error(e.localizedMessage ?: "")
            }
        }
    }
}