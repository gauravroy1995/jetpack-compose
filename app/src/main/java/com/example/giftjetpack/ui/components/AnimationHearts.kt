package com.example.giftjetpack.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.giftjetpack.R
import kotlin.random.Random

@Composable
fun AnimationHearts(){

    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp
    val screenWidth = configuration.screenWidthDp

    val infiniteTransition = rememberInfiniteTransition(label = "")
    val infiniteTransition3 = rememberInfiniteTransition(label = "")

    // Animate the vertical position of the hearts
    val offsetY by infiniteTransition.animateFloat(
        initialValue = 1.0f, // Start from the bottom
        targetValue = 0.0f, // Float to the top
        animationSpec = infiniteRepeatable(
            animation = tween(5000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )



    // Animate the size of the hearts
    val heartSize by infiniteTransition3.animateFloat(
        initialValue = 20.0f,
        targetValue = 60.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(5000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    // Create a list of heart positions for multiple hearts
    val heartPositions =  List(20) {
        Offset(
            x =  remember { ( Random.nextFloat()) * screenWidth} , // Random horizontal position
            y = (offsetY * screenHeight) // Vertical position controlled by offsetY
        )
    }



        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            heartPositions.forEach { position ->
                Image(
                    painter = painterResource(R.drawable.heart),
                    contentDescription = "heart",
                    modifier = Modifier
                        .size(heartSize.dp)
                        .offset(x = position.x.dp, y = position.y.dp)
                )
            }
        }


}