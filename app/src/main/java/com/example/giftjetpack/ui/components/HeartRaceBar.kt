package com.example.giftjetpack.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.giftjetpack.R
import com.example.giftjetpack.ui.viewModel.RaceParticipant
import com.example.giftjetpack.ui.viewModel.progressFactor
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun HeartRaceBar(onClick: () -> Unit) {


    fun onRaceFinishClick() {
        onClick()
    }


    val elseHeart = remember { RaceParticipant(progressDelayMillis = 600, progressIncrement = 3) }
    val myHeart = remember {
        RaceParticipant(
            progressDelayMillis = 500,
            progressIncrement = 6,
            initialDelay = 1000L,
            onRaceFinish = { onRaceFinishClick() })
    }

    val infiniteTransition = rememberInfiniteTransition(label = "")
    // Animate the size of the hearts
    val firstHeartSize by infiniteTransition.animateFloat(
        initialValue = 20.0f,
        targetValue = 40.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    val secondHeartSize by infiniteTransition.animateFloat(
        initialValue = 50.0f,
        targetValue = 100.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )



    LaunchedEffect(Unit) {

        coroutineScope {
            launch { elseHeart.run() }
            launch { myHeart.run() }
        }
    }


    val elseProgress = elseHeart.progressFactor
    val myProgress = myHeart.progressFactor


    Column(verticalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxSize()) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxHeight(0.3f)
                .padding(start = 20.dp)
        ) {
            Column {
                LinearProgressIndicator(
                    progress = { elseProgress },
                    modifier = Modifier.width(200.dp)
                )
                Text(text = "My normal heart")
            }

            Spacer(modifier = Modifier.width(16.dp)) // Add some spacing between the progress bar and the image
            Image(
                painter = painterResource(R.drawable.heart),
                contentDescription = "heart",
                modifier = Modifier.size(firstHeartSize.dp)
            )
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .padding(start = 20.dp)
        ) {
            Column {
                LinearProgressIndicator(
                    progress = { myProgress },
                    modifier = Modifier.width(200.dp)
                )
                Text(text = "My heart when i see YOU")
            }
            Spacer(modifier = Modifier.width(16.dp)) // Add some spacing between the progress bar and the image
            Image(
                painter = painterResource(R.drawable.heart),
                contentDescription = "heart",
                modifier = Modifier.size(secondHeartSize.dp)
            )
        }
    }


}