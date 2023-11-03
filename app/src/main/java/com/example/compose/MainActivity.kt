@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.compose


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.ui.screen.VegetableApp
import com.example.compose.ui.theme.ComposeTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivitys", "oncreate")
        setContent {
            ComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),

                    ) {
                    VegetableApp()
                }
            }
        }
    }


    override fun onStart() {
        super.onStart()
        Log.d("MainActivitys", "onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivitys", "onStop")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivitys", "onPause")
    }

    override fun onResume() {
        Log.d("MainActivitys", "onResume")
        super.onResume()

    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ComposeTheme() {
        VegetableApp()
    }
}