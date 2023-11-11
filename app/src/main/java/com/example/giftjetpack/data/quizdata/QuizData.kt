package com.example.giftjetpack.data.quizdata

import com.example.giftjetpack.R

class QuizData {

    private var quizQuestions: MutableList<QuizDataEach> = mutableListOf()

    init {
        loadQuestions()
    }


    fun loadQuestions() {

        val sampleData = listOf(
            QuizDataEach(
                answer = "24Feb2023",
                question = "When did we attend our first ever concert",
                image = R.drawable.concert,
                isTimeQuestion = true
            ),
            QuizDataEach(
                answer = "27Jul2022",
                question = "When did i first call you / whatsapp you?",
                isTimeQuestion = true
            ),
            QuizDataEach(
                answer = "vikram",
                image = R.drawable.pok,
                question = "Which movie did vishali watch while we were playing poker at chinmay house?",
            ),
            QuizDataEach(
                answer = "maggie",
                question = "Ankits cat name?",
                image = R.drawable.cat,
                radioArray = listOf("lucy", "maggie", "vikram", "momo"),
                isRadioQuestion = true
            ),
            QuizDataEach(
                answer = "momo",
                image = R.drawable.bowl,
                question = "What is the name of the thing we ate just before bowling?",
            ),
            QuizDataEach(
                answer = "19Feb2023",
                image = R.drawable.cycl,
                question = "When did you first attempt to cycle?",
                isTimeQuestion = true
            ),
            QuizDataEach(
                answer = "Street Storyys",
                image = R.drawable.rest,
                question = "What is the name of the restuarent which DV bhai recommended?",
                radioArray = listOf("Street Storyys", "Shiro", "Mikasu", "Oyster bar and kitchen"),
                isRadioQuestion = true
            ),
            QuizDataEach(
                answer = "Strawberry",
                image = R.drawable.pudu,
                question = "Which flavour CD did we buy in puducherry?",
                radioArray = listOf("Strawberry", "Lime", "Chocolate", "Butterscotch"),
                isRadioQuestion = true
            ),
        )

        quizQuestions.addAll(sampleData)

    }


    fun getQuizQuestions(): MutableList<QuizDataEach> {
        return quizQuestions
    }
}