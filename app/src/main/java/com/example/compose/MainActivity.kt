@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.compose


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.compose.ui.theme.ComposeTheme


//const val nameI = "https://images.unsplash.com/photo-1607604276583-eef5d076aa5f?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8YW5pbWV8ZW58MHx8MHx8fDA%3D&auto=format&fit=crop&w=800&q=60"
//"https://images.unsplash.com/photo-1560972550-aba3456b5564?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTh8fGFuaW1lfGVufDB8fDB8fHww&auto=format&fit=crop&w=800&q=60"
//"https://plus.unsplash.com/premium_photo-1682124752476-40db22034a58?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8YW5pbWV8ZW58MHx8MHx8fDA%3D&auto=format&fit=crop&w=800&q=60"

data class MyItem(
    val title: String,
    val description: String,
    val imageUrl: String
)


val myMutableObjectList: MutableList<MyItem> = mutableListOf(
    MyItem(
        "Title 1",
        "Description 1",
        "https://images.unsplash.com/photo-1607604276583-eef5d076aa5f?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8YW5pbWV8ZW58MHx8MHx8fDA%3D&auto=format&fit=crop&w=800&q=60"
    ),
    MyItem(
        "Title 2",
        "Description 2",
        "https://images.unsplash.com/photo-1560972550-aba3456b5564?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTh8fGFuaW1lfGVufDB8fDB8fHww&auto=format&fit=crop&w=800&q=60"
    ),
    MyItem(
        "Title 3",
        "Description 3",
        "https://plus.unsplash.com/premium_photo-1682124752476-40db22034a58?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8YW5pbWV8ZW58MHx8MHx8fDA%3D&auto=format&fit=crop&w=800&q=60"
    )
)


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
                    RenderGreeting()
                }
            }
        }
    }
}


@Composable
fun RenderThings(currData: MyItem) {
    val image = currData.imageUrl;
    val title = currData.title
    val description = currData.description
    Box(
        modifier = Modifier
            .border(1.dp, Color.Black)
            .background(Color.White)
    ) {
        AsyncImage(
            model = image, contentDescription = null,
            modifier = Modifier
                .height(200.dp)
                .width(200.dp)
                .padding(10.dp),
            contentScale = ContentScale.FillBounds,

            )
    }
    Text(text = title)
    Text(text = description)
}


@Composable
fun RenderButtons(currIndex: Int = 0, onNextPress: () -> Unit, onPrevPress: () -> Unit) {
    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
        if (currIndex > 0) {
            Button(onClick = { onPrevPress() }) {
                Text(text = "Previous")
            }
        }

        if (currIndex < myMutableObjectList.size - 1) {
            Button(onClick = onNextPress) {
                Text(text = "Next")
            }
        }
    }
}


@Composable
fun RenderGreeting() {

    var currIndex by remember { mutableStateOf(0) }


    var currData: MyItem = myMutableObjectList[currIndex]


    fun onNextPress() {
        if (currIndex < myMutableObjectList.size - 1) {
            Log.d("Gaurav drag press cuteee","press ${currIndex}")
            currIndex += 1

        }
    }

    fun onPrevPress() {
        Log.d("Gaurav drag press","press")
        if (currIndex > 0) {
            Log.d("Gaurav drag press nowww","press")
currIndex -= 1
        }
    }



    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().pointerInput(Unit) {
            var totalDrag = 0f
            detectHorizontalDragGestures(onHorizontalDrag = {change, dragAmount -> totalDrag+= dragAmount},
                onDragStart = {}, onDragEnd = {
                    if(totalDrag > 0) {
                        onNextPress()
                    } else {
                        onPrevPress()
                    }
//                    totalDrag = 0f
                    Log.d("Gaurav drag", "onDragEnd: $totalDrag")

                }, onDragCancel = {})
        }
    ) {
        RenderThings(currData)
        RenderButtons(currIndex, {
            onNextPress()
        }, { onPrevPress() })
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ComposeTheme {
        RenderGreeting()
    }
}