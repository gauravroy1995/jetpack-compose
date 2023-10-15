@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.compose


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.ui.theme.ComposeTheme


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
fun RenderTextInput(billString:String, onValueChangeNew:(String)->Unit, modifier: Modifier = Modifier) {
    TextField(
        value = billString,
        onValueChange = onValueChangeNew ,
        singleLine = true,
        label = { Text("Hello") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier
    )
}

@Composable
fun checkTip(amount:String) : String{
    var total = amount.toDoubleOrNull() ?: 0.0
    var tip = total * 0.15;
    return tip.toString()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RenderTipTextBox(modifier: Modifier) {


    var (billString,setBillString) =  remember { mutableStateOf("") }

    val tip = checkTip(billString)



    
    Column() {
        RenderTextInput(billString, { setBillString(it.toString()) })

        Text("Your tip $tip", modifier = Modifier
            .fillMaxWidth()
            .size(80.dp), fontSize = 32.sp)

    }
   

}




@Composable
fun RenderGreeting(name: String, modifier: Modifier = Modifier) {

    RenderTipTextBox(Modifier.padding(5.dp))

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ComposeTheme {
        RenderGreeting(nameI)
    }
}