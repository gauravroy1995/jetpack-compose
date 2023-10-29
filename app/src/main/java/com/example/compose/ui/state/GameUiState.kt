package com.example.compose.ui.state

data class GameUiState(
    val currentScrambledWord: String = "",
    val isGuessedWordWrong: Boolean = false,
    val score: Int = 0,
    val guessCount: Int = 1,
    val totalQuestions: Int = 5,
    val isGameOver: Boolean = false
)
