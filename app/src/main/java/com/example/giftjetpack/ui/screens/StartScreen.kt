package com.example.giftjetpack.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.giftjetpack.ui.components.HeartRaceBar


@Composable
fun StartScreen(onClick: () -> Unit) {


    var showProgressBars by remember { mutableStateOf(false) }

    fun onClickButton() {
        Log.d("StartScreen", "onClickButton")
        showProgressBars = true

    }




    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {


        if (showProgressBars) {
            HeartRaceBar(onClick = onClick)
        } else {
            Button(
                onClick = { onClickButton() },
            ) {
                Text(text = "Let's Start")
            }
        }

    }
}