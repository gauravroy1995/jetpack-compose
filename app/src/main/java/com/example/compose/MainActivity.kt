@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.compose


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.ui.theme.ComposeTheme
import java.text.DecimalFormat
import java.util.Currency
import java.util.Locale
import kotlin.math.roundToInt


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



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RenderTextInput(billString:String, onValueChangeNew:(String)->Unit, modifier: Modifier = Modifier) {
    TextField(
        value = billString,
        onValueChange = onValueChangeNew ,
        singleLine = true,
        label = { Text("Total Amount") },
        leadingIcon = { Icon(painter = painterResource(id = R.drawable.money), contentDescription = null)},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RenderTipPercent(billString:String, onValueChangeNew:(String)->Unit, modifier: Modifier = Modifier.padding(top = 20.dp)) {
    TextField(
        value = billString,
        onValueChange = onValueChangeNew ,
        singleLine = true,
        leadingIcon = { Icon(painter = painterResource(id = R.drawable.tip), contentDescription = null)},
        label = { Text("Tip Percent") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier
    )
}

@Composable
fun RenderSwitch(round:Boolean, onValueChangeNew:(Boolean)->Unit, modifier: Modifier = Modifier
    .padding(top = 10.dp, start = 60.dp, end = 60.dp)
    .fillMaxWidth()){
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier =modifier ){
        Text("Round off")
        Switch(checked = round, onCheckedChange =onValueChangeNew)
    }
}

@Composable
fun checkTip(amount: String, tip: Double = 0.0, isRound: Boolean = false): String {
    var total = amount.toDoubleOrNull() ?: 0.0
    var tipString = total * tip;
    if (isRound) {
        tipString = tipString.roundToInt().toDouble()
    }

    val decimalFormat = DecimalFormat("#.##")

    return decimalFormat.format(tipString)
}

@Composable
fun calculateTipPercent(amount: String): Double {
    val tip =  amount.toDoubleOrNull() ?: 0.0;
    return tip/100;
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RenderTipTextBox(modifier: Modifier) {


    var (billString,setBillString) =  remember { mutableStateOf("") }
    var (tipPercent,setTipPercent) =  remember { mutableStateOf("") }
    var (round,setRound) =  remember { mutableStateOf(false) }

    val tipPercentDouble = calculateTipPercent(tipPercent)
    val tip = checkTip(billString,tipPercentDouble,round)


    val currencyCode = Currency.getInstance(Locale.getDefault()).currencyCode
    val currencySymbol = Currency.getInstance(currencyCode).getSymbol(Locale.getDefault())

    
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        RenderTextInput(billString, { setBillString(it) })
        RenderTipPercent(tipPercent, { setTipPercent(it) })
        RenderSwitch(round, { setRound(it) })

        Text("Your tip $currencySymbol$tip", modifier = Modifier,fontSize = 32.sp)

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