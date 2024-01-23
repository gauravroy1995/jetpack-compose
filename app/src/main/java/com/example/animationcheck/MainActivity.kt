package com.example.animationcheck

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.animationcheck.ui.theme.AnimationCheckTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        setContent {
            Home()
        }
    }
}


@Composable
fun Home(mainViewModel: MainViewModel = viewModel()) {

    val showAnim = mainViewModel.showAnimationState.collectAsState()

    Log.d("Home", "Home: ${showAnim.value}")


    AnimationCheckTheme {
        Scaffold(
            topBar = { Header() }, modifier = Modifier.fillMaxSize()

        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                FlatList(mainViewModel)
            }

            AnimatedIconMovement(showAnim.value)

        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AnimationCheckTheme {
        Greeting("Android")
    }
}