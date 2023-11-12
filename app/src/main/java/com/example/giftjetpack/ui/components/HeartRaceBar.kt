package com.example.giftjetpack.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HeartRaceBar(elseProgress: Float, myProgress: Float) {


    Log.d("HeartRaceBar", "elseProgress: $elseProgress")
    Column {
        LinearProgressIndicator(
            progress = { 0.2f },
            modifier = Modifier
                .width(200.dp)
                .padding(40.dp),
        )

        LinearProgressIndicator(
            progress = { myProgress },
            modifier = Modifier
                .width(200.dp),
        )
    }


//    LaunchedEffect(Unit) {
//        Log.d("HeartRaceBar", "LaunchedEffect")
//        coroutineScope {
//            launch { elseHeart.run() }
//            launch { myHeart.run() }
//        }
//
//    }

}