package com.example.giftjetpack.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.giftjetpack.data.quizdata.QuizData
import com.example.giftjetpack.data.quizdata.QuizDataEach
import com.example.giftjetpack.ui.components.ImageRender
import com.example.giftjetpack.ui.components.RadioPicker
import com.example.giftjetpack.ui.components.TextInputEntry
import com.example.giftjetpack.ui.components.TimePickerEntry
import com.example.giftjetpack.ui.viewModel.QuizkViewModel
import com.example.giftjetpack.ui.viewModel.returnRandomNumberInSize
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(viewModel: QuizkViewModel, onFinish: () -> Unit) {

    val context = LocalContext.current

    var questions = QuizData().getQuizQuestions()

    var isFirstTimeLaunched by remember { mutableStateOf(false) }
    var otherThanTimeAnswers by remember { mutableStateOf("") }


    val questionShownArray = viewModel.uiState.collectAsState().value.questionListIndexes
    val currQuestionIndex = viewModel.uiState.collectAsState().value.currQuestion


    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = Date().time,
        initialDisplayMode = DisplayMode.Picker,
        selectableDates = object : SelectableDates {
            // Allow selecting dates from year 2023 forward.
            override fun isSelectableYear(year: Int): Boolean {
                if (year in 2021..2023) {
                    return true
                }
                return false
            }

        })

    val dateFormatter = remember { SimpleDateFormat("ddMMMyyyy", Locale.US) }




    LaunchedEffect(isFirstTimeLaunched) {
        if (!isFirstTimeLaunched) {
            val randomNumber = returnRandomNumberInSize(questions.size, questionShownArray)
            viewModel.setCurrQuestionIndex(randomNumber)
            isFirstTimeLaunched = true
        }
    }

    Log.d("TAG", "QuizScreen: $questionShownArray")

    if (currQuestionIndex == null) {
        return
    }

    val questionSelected: QuizDataEach = questions[currQuestionIndex]

    val questionText = questionSelected.question

    val isRadioQuestion = questionSelected.isRadioQuestion

    val isTimeQuestion = questionSelected.isTimeQuestion

    val isFinalQuestion = questionShownArray.size == questions.size

    fun buttonTextDecider(): String {
        if (isFinalQuestion) {
            return "Submit"
        }
        return "Next"
    }


    fun showToast() {
        Toast.makeText(context, "Wrong Answer", Toast.LENGTH_SHORT).show()
    }


    fun onRightAnswers() {
        if (isFinalQuestion) {
            onFinish()
            Toast.makeText(context, "Right Answer", Toast.LENGTH_SHORT).show()
        } else {
            viewModel.onNextQuestionClick()
        }
    }

    fun onNextClick() {
        // Only time question handling
        if (isTimeQuestion) {
            val answerTime = dateFormatter.format(Date(datePickerState.selectedDateMillis!!))
            if (answerTime == questionSelected.answer) {
                onRightAnswers()
            } else {
                showToast()
            }

            return
        }

        // Only radio question handling
        if (isRadioQuestion) {
            if (otherThanTimeAnswers == questionSelected.answer) {
                onRightAnswers()
                otherThanTimeAnswers = ""
            } else {
                showToast()
            }

            return
        }

        if (otherThanTimeAnswers.lowercase(Locale.ROOT) == questionSelected.answer.lowercase(Locale.ROOT)) {
            onRightAnswers()
            otherThanTimeAnswers = ""
            return
        } else {
            showToast()
        }


    }

    fun onRadioButtonClick(answer: String) {
        otherThanTimeAnswers = answer
    }

    fun onTextChangeInput(answer: String) {
        otherThanTimeAnswers = answer
    }

    @Composable
    fun MainBody() {
        if (isTimeQuestion) {
            return TimePickerEntry(datePickerState = datePickerState)
        }

        if (isRadioQuestion) {
            // Radio button handling
            return RadioPicker(
                questionSelected,
                onOptionSelected = { it -> onRadioButtonClick(it) },
                selectedAnswer = otherThanTimeAnswers
            )
        }

        return TextInputEntry(
            textValue = otherThanTimeAnswers,
            onTextChange = { it -> onTextChangeInput(it) },
            onNextPress = { onNextClick() }
        )

    }

    @Composable
    fun BottomBar() {
        Column(
            modifier = Modifier
                .background(Color(0xFF333131))
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { onNextClick() }) {
                Text(text = buttonTextDecider())
            }
        }
    }


    /*
    * UI starts from here
    */
    Scaffold(
        bottomBar = {
            BottomBar()
        },

        ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = questionText,
                fontSize = 30.sp,
                modifier = Modifier.padding(vertical = 20.dp, horizontal = 20.dp),
                textAlign = TextAlign.Center
            )

            ImageRender(imageId = questionSelected.image)


            MainBody()


        }
    }

}








