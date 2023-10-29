package com.example.compose.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.compose.data.allWords
import com.example.compose.ui.state.GameUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {
    // Game UI state
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private lateinit var currentWord: String
    private var usedWords: MutableSet<String> = mutableSetOf()

    var userGuess by mutableStateOf("")
        private set

    init {
        resetGame()
    }

    private fun pickRandomWordAndShuffle(): String {
        // Continue picking up a new random word until you get one that hasn't been used before
        currentWord = allWords.random()
        if (usedWords.contains(currentWord)) {
            return pickRandomWordAndShuffle()
        } else {
            usedWords.add(currentWord)
            return shuffleCurrentWord(currentWord)
        }
    }



    private fun shuffleCurrentWord(word: String): String {
        val tempWord = word.toCharArray()
        // Scramble the word
        tempWord.shuffle()
        while (String(tempWord).equals(word)) {
            tempWord.shuffle()
        }
        return String(tempWord)
    }

    fun resetGame() {
        usedWords.clear()
        _uiState.value = GameUiState(currentScrambledWord = pickRandomWordAndShuffle(),isGuessedWordWrong = false, score = 0, guessCount = 0, isGameOver = false)
    }

    fun updateUserGuess(guessedWord: String){
        userGuess = guessedWord
    }

    fun checkUserGuess() {

        if(_uiState.value.guessCount == _uiState.value.totalQuestions){
            _uiState.update { currentState ->
                currentState.copy(isGameOver = true, isGuessedWordWrong = false,)
            }
            return

        }

        if (userGuess.equals(currentWord, ignoreCase = true)) {
            _uiState.update { currentState ->
                val newScore = currentState.score + 1
                val newGuessCount = currentState.guessCount + 1
                currentState.copy(isGuessedWordWrong = false, score = newScore, currentScrambledWord = pickRandomWordAndShuffle(), guessCount = newGuessCount)
            }
        } else {

            // User's guess is wrong, show an error
            _uiState.update { currentState ->
                currentState.copy(isGuessedWordWrong = true)
            }
        }
        // Reset user guess
        updateUserGuess("")
    }

    fun skipWord() {
        if(_uiState.value.guessCount == _uiState.value.totalQuestions){
            _uiState.update { currentState ->
                currentState.copy(isGameOver = true, isGuessedWordWrong = false,)
            }
            return

        }
        _uiState.update { currentState ->
            val newGuessCount = currentState.guessCount + 1
            currentState.copy(isGuessedWordWrong = false, currentScrambledWord = pickRandomWordAndShuffle(), guessCount = newGuessCount)
        }
        updateUserGuess("")
    }

}