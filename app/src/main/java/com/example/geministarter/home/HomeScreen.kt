package com.example.geministarter.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.geministarter.R
import com.example.geministarter.ScreenNames
import kotlin.math.max


data class MenuItem(
    val routeName: String,
    val titleResId: Int,
    val descriptionResId: Int,
    val exampleString: String
)


@Composable
fun HomeScreen(navController: NavController) {

    val menuItems: List<MenuItem> = listOf(
//        MenuItem(
//            ScreenNames.START_SCREEN.name,
//            R.string.normal_search_title,
//            R.string.normal_search,
//            "How far is the sun?"
//        ),
        MenuItem(
            ScreenNames.NUTRI_SCREEN.name,
            R.string.nutri_search_title,
            R.string.basic_default,
            "whats the nutri value of the image i clicked and some recipes with it?"
        ),
    )

    Scaffold(
        topBar={
            HistoryHeader(navigateToHistory = {
                navController.navigate(ScreenNames.HISTORY_SCREEN.name)
            })
        }
    ){
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            items(menuItems) { menuItem ->
                MenuItemCard(menuItem, navController)
            }
        }
    }


}


@Composable
fun HistoryHeader(navigateToHistory: () -> Unit){
    Row(horizontalArrangement = Arrangement.SpaceBetween,modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.onSurface)) {
        Text(
            text = "AI Models",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            ),
            color = Color.White,
            modifier = Modifier.padding(all = 20.dp)
        )
        Button(onClick = { navigateToHistory() }, modifier = Modifier.padding(all = 10.dp)) {
            Text(
                text = "History",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                ),
                color = Color.White,

            )
        }

    }
}


@Composable
fun MenuItemCard(menuItem: MenuItem, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(menuItem.routeName)
            }
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(16.dp)
    ) {

        val text = stringResource(id = menuItem.descriptionResId)


        val substring = menuItem.exampleString

        val annotatedString = buildAnnotatedString {
            // Determine the starting index for the substring
            append(text)

            val startIndex = max(0, text.length)

//            // Append the remaining part of the text
//            append(text.substring(0, startIndex))

            // Append the styled "how far is sun" at the end
            withStyle(style = SpanStyle(color =MaterialTheme.colorScheme.onSurface, fontSize = 16.sp, fontWeight = FontWeight.Bold)) {
                append(substring)
            }
        }

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = menuItem.titleResId),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                ),
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = annotatedString,
                style = TextStyle(
                    fontSize = 16.sp
                )
            )
        }
    }
}



