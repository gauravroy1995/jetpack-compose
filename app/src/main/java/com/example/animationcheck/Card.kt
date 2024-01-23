package com.example.animationcheck

import android.util.Log
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest.Builder
import kotlinx.coroutines.launch


val imageUrl =
    "https://images.unsplash.com/photo-1515443961218-a51367888e4b?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=200"

@Composable
fun CardWrap(item: Int = 0, viewModel: MainViewModel) {

    val context = LocalContext.current
    val density = LocalDensity.current
    val view = LocalView.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {


            val painter = rememberAsyncImagePainter(
                model = Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build()
            )
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth(),
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
                    .pointerInput(Unit) {
                        detectTapGestures(onPress = {
                            Log.d("Wuh", "Pressed dada ${it.y}")

                        })
                    }
            ) {
                Text(text = item.toString(), fontSize = 30.sp)
                Box(modifier = Modifier.pointerInput(Unit) {
                    detectTapGestures(onPress = {
                        Log.d("Wuh", "Pressed  dhajdahdjka${it.y}")

                    })
                }) {
                    Button(

                        onClick = {
                            viewModel.toggleAnimationState()

                        },
                        modifier = Modifier
                            .pointerInput(Unit) {
                                detectTapGestures(onPress = {
                                    Log.d("Wuh", "Pressed ${it.y}")

                                })
                            }
                            .onGloballyPositioned {
                                val positionInRoot = it.positionInRoot()
                                println("🔥 POSITION 1: $positionInRoot")
                            }
                            .onSizeChanged {
                                println("🔥 POSITION 2: $it")
                            },

                        ) {
                        Text(text = "Collect")
                    }
                }


//                Icon(painter = painterResource(id = R.drawable.bitcoin), contentDescription = null , modifier = Modifier.background(
//                    Color.Yellow) )
            }


        }
    }
}


@Composable
fun AnimatedIconMovement(animValue: Boolean = false) {
//    var isIconAtTop by remember { mutableStateOf(false) }
//
//    // Create an alpha and translation animation
//    val alpha by animateFloatAsState(
//        targetValue = if (isIconAtTop) 0f else 1f,
//        animationSpec = tween(durationMillis = animDuration, easing = LinearEasing)
//    )
//
//    val translationY by animateFloatAsState(
//        targetValue = if (isIconAtTop) (-300).toFloat() else (300).toFloat(),
//        animationSpec = tween(durationMillis = animDuration, easing = LinearEasing)
//    )
//
//    // Use a Box to position the icon
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        Icon(
//            imageVector = Icons.Default.AccountBox,
//            contentDescription = null,
//            tint = Color.White,
//            modifier = Modifier
////                .graphicsLayer(alpha = alpha)
//                .offset(y = translationY.dp)
//                .clickable {
//                    isIconAtTop = !isIconAtTop
//                }
//        )
//    }
//
//    var isAnimated by remember { mutableStateOf(false) }
//    val transition = updateTransition(targetState = isAnimated, label = "transition")
//
//    val animDuration = 200
//
//    val isAnimationRuning = transition.isRunning
//
//    Log.d("Wuh", "isAnimationRuning $isAnimationRuning")
//
//    val rocket1Offset by transition.animateOffset(
//        transitionSpec = {
//            if (this.targetState) {
//                tween(500) // launch duration
//            } else {
//                tween(1500) // land duration
//            }
//        },
//        label = "rocket1 offset"
//    ) { animated ->
//        if (animated) Offset(200f, 0f) else Offset(200f, 500f)
//    }
//
//    val rocket2Offset by transition.animateOffset(
//        transitionSpec = {
//            if (this.targetState) {
//                tween(
//                    animDuration,
//                    delayMillis = 500,
//                    easing = LinearOutSlowInEasing
//                ) // launch duration
//            } else {
//
//                tween(
//                    1500,
//                    delayMillis = animDuration,
//                    easing = LinearOutSlowInEasing
//                ) // land duration
//            }
//        },
//        label = "rocket2 offset"
//    ) { animated ->
//        if (animated) Offset(200f, 0f) else Offset(200f, 500f) // Adjust x position for rocket2
//    }
//
//    val rocket3Offset by transition.animateOffset(
//        transitionSpec = {
//            if (this.targetState) {
//                tween(
//                    animDuration,
//                    delayMillis = 750,
//                    easing = LinearOutSlowInEasing
//                ) // launch duration
//            } else {
//                tween(1500, delayMillis = 2000) // land duration
//            }
//        },
//        label = "rocket3 offset"
//    ) { animated ->
//        if (animated) Offset(200f, 0f) else Offset(200f, 500f) // Adjust x position for rocket3
//    }
//
//    val alpha1 by transition.animateFloat(
//        transitionSpec = {
//            if (this.targetState) {
//                tween(500) // launch duration
//            } else {
//                tween(1500) // land duration
//            }
//        },
//        label = "rocket1 offset"
//    ) { animated ->
//        if (animated && isAnimationRuning) 1f else 0f
//
//    }
//
//
//    val rocketSize by transition.animateDp(transitionSpec = {
//        tween(animDuration)
//    }, "") { animated ->
//        if (animated) 30.dp else 30.dp
//    }
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .zIndex(1f)
//    ) {
//        repeat(3) { index ->
//            var xDp = 0.dp
//            var yDp = 0.dp
//
//            val alphaValue = if (isAnimated) {
//                val progress = transition.animateFloat(
////                    transitionSpec = { tween(durationMillis = 0) },
//                    label = "alpha"
//                ) { animated ->
//                    if (animated) 1f else 0f
//                }
//
//                if (progress.value == 1f) {
//                    0f
//                } else {
//                    1f
//                }
//
//                progress.value
//            } else {
//                0f
//            }
//
//            if (isAnimated) {
//                when (index) {
//                    0 -> {
//                        xDp = rocket1Offset.x.dp
//                        yDp = rocket1Offset.y.dp
//                    }
//
//                    1 -> {
//
//                        xDp = rocket2Offset.x.dp
//                        yDp = rocket2Offset.y.dp
//                    }
//
//                    2 -> {
//
//                        xDp = rocket3Offset.x.dp
//                        yDp = rocket3Offset.y.dp
//                    }
//                }
//            }
//
//            Icon(
//                Icons.Filled.RemoveCircle,
//                tint = Color.Yellow,
//                contentDescription = "Rocket",
//                modifier = Modifier
//                    .size(rocketSize)
//                    .alpha(alpha1)
//                    .offset(
//                        x = xDp,
//                        y = yDp
//                    )
//            )
//        }
//
//        Button(
//            onClick = { isAnimated = !isAnimated },
//            modifier = Modifier.padding(top = 10.dp)
//        ) {
//            Text(text = if (isAnimated) "Land rockets" else "Launch rockets")
//        }
//    }


    val transition = updateTransition(animValue, label = "iconstate")

    val scope = rememberCoroutineScope()

    val colorEffect = remember { Animatable(Color.Red)}

    LaunchedEffect(key1 = animValue) {

        //sequence animation if i add another scope.launch then it will be parallel
      /*
      * for suppose
      * scope.launch{
      *
      * anim1()
      * }
      *
      * scope.launch{
      *
      * anim2()
      * }
      *
      * will be in pralle
      *
      *
      *
      * */
        scope.launch {
            colorEffect.animateTo(
               targetValue = Color.Yellow,
                animationSpec = tween(1000)
            )
            colorEffect.animateTo(
                targetValue = Color.Blue,
                animationSpec = tween(1000)
            )
        }

    }

    val offsetVal by transition.animateFloat(
        label = "rocket1 offset",
        transitionSpec = {

            keyframes {
                durationMillis = 2000
                0f at 0
                1f at 600
                0.5f at 1000
                1f at 1800
            }
        },
        targetValueByState = { isAnim ->
            if (isAnim) 0f else 0f

        },
    )

    val sizeAnimate by transition.animateDp(label ="size dp", targetValueByState = {
        if(it) 60.dp else 20.dp
    })

    val animateVal by animateFloatAsState(
        targetValue = if (animValue) 1f else 0f,
        label = "animateVal",
        animationSpec = tween(1000),
        finishedListener = {
            Log.d("Wuh", "AnimatedIconMovement: $it $animValue")
        }
    )

    LaunchedEffect(animateVal) {
        if (animateVal == 1f) {
            Log.d("Wuh", "AnimatedIconMovement 22: ")
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(2f)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {


            Icon(
                Icons.Filled.RemoveCircle,
                contentDescription = null,
                tint = colorEffect.value,
                modifier = Modifier
                    .size(20.dp)
//                    .alpha(offsetVal)
            )

        }
    }


}