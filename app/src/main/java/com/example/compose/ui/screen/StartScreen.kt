package com.example.compose.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun StartScreen(onClick: () -> Unit){

    Column() {
        Text(text = "Start Screen")
        Button(onClick = onClick) {
            Text(text = "Start")
        }
    }
}