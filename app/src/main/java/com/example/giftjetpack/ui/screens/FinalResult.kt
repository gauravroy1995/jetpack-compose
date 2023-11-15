package com.example.giftjetpack.ui.screens

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.giftjetpack.ui.components.AnimationHearts
import kotlinx.coroutines.delay


@Composable
fun FinalResult() {
    val activity = (LocalContext.current as? Activity)

    LaunchedEffect(Unit) {
        // Delay for 5 seconds and then close the app
        delay(10000)

        activity?.finish()

    }


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AnimationHearts()
        Text(text = "You know us pretty well, wuhuu !! LOVE YOU")
    }
}