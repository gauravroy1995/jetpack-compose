package com.example.roomwithapi

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roomwithapi.screens.NavScreen
import com.example.roomwithapi.ui.theme.RoomWithApiTheme
import com.example.roomwithapi.viewmodel.AppViewModelProvider
import com.example.roomwithapi.viewmodel.HomeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoomWithApiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier,
             viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
             ) {

    val busUiState by viewModel.homeUiState.collectAsState()

    val busScheduleList = busUiState.itemList

    Log.d("MainActivity", "busScheduleList: $busScheduleList")
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RoomWithApiTheme {
        Greeting("Android")
    }
}