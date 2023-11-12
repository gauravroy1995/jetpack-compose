package com.example.giftjetpack.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.giftjetpack.ui.components.HeartRaceBar
import com.example.giftjetpack.ui.viewModel.RaceParticipant
import com.example.giftjetpack.ui.viewModel.progressFactor
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


@Composable
fun StartScreen(onClick: () -> Unit) {

    val elseHeart = RaceParticipant(progressDelayMillis = 0)
    val myHeart = RaceParticipant(progressDelayMillis = 500, progressIncrement = 2)

    val elseProgress = elseHeart.progressFactor
    val myProgress = myHeart.progressFactor

    Log.d("StartScreen", "elseProgress: $elseProgress")

    var showProgressBars by remember { mutableStateOf(false) }

    fun onClickButton() {
        Log.d("StartScreen", "onClickButton")
        showProgressBars = true

    }

    if (showProgressBars) {
        LaunchedEffect(Unit) {
            Log.d("HeartRaceBar", "LaunchedEffect")
            coroutineScope {
                launch { elseHeart.run() }
                launch { myHeart.run() }
            }

        }
    }


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
//        AnimationHearts()

        if (showProgressBars) {
            HeartRaceBar(elseProgress = elseProgress, myProgress = myProgress)
        } else {
            Button(
                onClick = { onClickButton() },
            ) {
                Text(text = "Let's Start")
            }
        }

    }
}