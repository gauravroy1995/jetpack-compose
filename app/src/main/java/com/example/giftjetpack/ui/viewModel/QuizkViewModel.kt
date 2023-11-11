package com.example.giftjetpack.ui.viewModel


import androidx.lifecycle.ViewModel
import com.example.giftjetpack.data.quizdata.QuizData

import com.example.giftjetpack.ui.state.QuizkUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class QuizkViewModel:ViewModel() {
    /**
     * Vegetable state for this order
     */
    private val _uiState = MutableStateFlow(QuizkUiState())
    val uiState: StateFlow<QuizkUiState> = _uiState.asStateFlow()


    fun setCurrQuestionIndex(questionIndex:Int){
        _uiState.value=_uiState.value.copy(currQuestion = questionIndex,questionListIndexes = _uiState.value.questionListIndexes.toMutableList().apply {
            add(questionIndex)
        })
    }

    fun onNextQuestionClick(){
        if(_uiState.value.questionListIndexes.size == QuizData().getQuizQuestions().size){
            resetOrder()
            return
        }
        var randomNum = returnRandomNumberInSize(QuizData().getQuizQuestions().size,_uiState.value.questionListIndexes)
        setCurrQuestionIndex(randomNum)
    }


    fun resetOrder(){
        _uiState.value=QuizkUiState()
    }




}


fun returnRandomNumberInSize(size: Int,questionListIndexes:MutableList<Int>): Int {
    var random =  (0 until size).random()
    while (questionListIndexes.contains(random)){
        random =  (0 until size).random()
    }
    return random
}