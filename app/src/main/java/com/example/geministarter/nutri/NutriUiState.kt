package com.example.geministarter.nutri

sealed interface NutriUiState {

    /**
     * Empty state when the screen is first shown
     */
    data object Initial: NutriUiState

    /**
     * Still loading
     */
    data object Loading: NutriUiState

    /**
     * Text has been generated
     */
    data class Success(
        val outputText: String
    ): NutriUiState

    /**
     * There was an error generating text
     */
    data class Error(
        val errorMessage: String
    ): NutriUiState
}