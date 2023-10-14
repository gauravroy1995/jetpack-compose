package com.example.compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.ui.theme.ComposeTheme
import kotlin.random.Random


const val nameI = "India"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RenderGreeting(nameI)
                }
            }
        }
    }
}


@Composable
fun ImageCard(){
    val image = painterResource(id = R.drawable.staticimg)
    Image(painter =image , contentDescription =null , modifier = Modifier
        .height(100.dp)
        .width(100.dp)
        .clip(
            CircleShape
        ) , contentScale = ContentScale.FillBounds )
}

@Composable
fun EachCard(modifier: Modifier = Modifier
    .background(Color.Yellow)
    .padding(all = 100.dp)){
    Text(text = stringResource(R.string.jetpack_compose_tutorial),modifier = modifier)

}



@Composable
fun LogoAndName() {
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally , modifier = Modifier.background(Color.Red)) {
        Text(
            text = "Logo",
            fontSize = 24.sp
        )

        Text(
            text = "Your Name",
            fontSize = 20.sp
        )
    }

}

@Composable
fun BottomTexts() {
    Box (){
        Column(
            modifier = Modifier
                .padding(10.dp)
                .background(Color.Yellow)
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            // Bottom texts
            Text(text = "Text 1 937139137918319381")
            Text(text = "Text 2")
            Text(text = "Text 3")
        }
    }

}


fun getImageResource(imgNum: Int): Int {
    return when(imgNum){
        1 -> R.drawable.staticimg
        2 -> R.drawable.image2
        3 -> R.drawable.img3
        else -> R.drawable.staticimg
    }
}


fun rollButtonOnClick(currentImageName: Int, onNewImageName: (Int) -> Unit): () -> Unit {
    return {
        val randomResult = generateRandomResult()
        Log.d("MainActivity", "Random result: $randomResult")
        onNewImageName(randomResult)
    }
}

fun generateRandomResult(): Int {
    // Generates a random number between 1 and 3
    return Random.nextInt(1, 4)
}

@Composable
fun RenderDice() {
    var imageName by remember { mutableStateOf(2) }
    var finalImage = getImageResource(imageName)
    val image = painterResource(id = finalImage)
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(all = 5.dp).fillMaxSize(), verticalArrangement = Arrangement.Center){
        Image(painter = image, contentDescription =null , modifier = Modifier
            .width(200.dp)
            .height(200.dp), contentScale = ContentScale.FillBounds)
        Button(onClick = rollButtonOnClick(imageName) { newImageName ->
            imageName = newImageName
        },) {
            Text(text = "Roll")
        }
    }

}

@Composable
fun RenderGreeting(name: String, modifier: Modifier = Modifier) {
    val image = painterResource(id = R.drawable.staticimg)
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val screenWidth = configuration.screenWidthDp
    RenderDice()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ComposeTheme {
        RenderGreeting(nameI)
    }
}