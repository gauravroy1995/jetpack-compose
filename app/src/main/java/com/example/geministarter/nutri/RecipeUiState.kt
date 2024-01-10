package com.example.geministarter.nutri



sealed interface RecipeUiState {

    /**
     * Empty state when the screen is first shown
     */
    data object Initial: RecipeUiState

    /**
     * Still loading
     */
    data object Loading: RecipeUiState

    /**
     * Text has been generated
     */
    data class Success(
        val outputText: String
    ): RecipeUiState

    /**
     * There was an error generating text
     */
    data class Error(
        val errorMessage: String
    ): RecipeUiState
}