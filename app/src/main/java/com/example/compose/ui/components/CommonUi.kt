package com.example.compose.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.compose.R
import com.example.compose.ui.theme.CabinBoldFont
import com.example.compose.ui.theme.CabinBoldRegular
import java.util.Currency
import java.util.Locale

/**
 * Composable that displays formatted [price] that will be formatted and displayed on screen
 */
@Composable
fun FormattedPriceLabel(subtotal: String, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.subtotal_price, subtotal),
        modifier = modifier,
        style = MaterialTheme.typography.headlineSmall
    )
}

@Composable
fun EachRow(id:Int, title: String, price: Double,benefits:String,selected:Boolean,onClick:(Int) -> Unit, modifier: Modifier = Modifier){

    val userCurrency = Currency.getInstance(Locale.getDefault())
    val currencySymbol = userCurrency.symbol

    val finalStringPrice = "$currencySymbol$price"

    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier.clickable { onClick(id)  }) {
        RadioButton(selected = selected , onClick = {onClick(id)})
        Column {
            Text(text = title, fontFamily = CabinBoldFont, fontSize = 20.sp)
            Text(text = benefits,fontFamily = CabinBoldRegular)
            Text(text = finalStringPrice)

        }
    }
}


@Composable
fun RowButtons(secondButtonText:String = "Next",onFirstClick: () -> Unit, onSecondClick: () -> Unit, modifier: Modifier = Modifier){
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
        OutlinedButton(onClick = onFirstClick) {
            Text(text = stringResource(R.string.cancel))
        }
        Button(onClick = onSecondClick) {
            Text(text = secondButtonText)
        }
    }
}