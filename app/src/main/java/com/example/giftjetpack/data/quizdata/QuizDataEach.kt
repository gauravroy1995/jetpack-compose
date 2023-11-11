package com.example.giftjetpack.data.quizdata

data class QuizDataEach(
    val answer: String,
    val question: String,
    val radioArray:List<String>? = null,
    val image: Int? = null,
    val isRadioQuestion: Boolean = false,
    val isTimeQuestion: Boolean = false,
)
