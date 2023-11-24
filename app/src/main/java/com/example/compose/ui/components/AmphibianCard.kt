package com.example.compose.ui.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.compose.R
import com.example.compose.network.AmphibiansDataClass
import com.example.compose.network.EachBookClass
import com.example.compose.network.ItemClass
import com.example.compose.ui.screens.QuizClassScreensEnum
import com.example.compose.ui.screens.SharedUserViewModelComposition
import kotlinx.serialization.json.Json


@Composable
fun AmphibianCard(imageData: EachBookClass,navController: NavHostController = rememberNavController()) {


    val viewModel = SharedUserViewModelComposition.current

    val newModifier = Modifier.padding(2.dp).clickable {

        Log.d("AmphibianCard", "AmphibianCard: $imageData")
        viewModel.setData(imageData)

//        val eachBookJson = Json.encodeToString(EachBookClass.serializer(), imageData)

//        navController.navigate("${QuizClassScreensEnum.BookDetailsScreen.name}/$eachBookJson")

        navController.navigate("${QuizClassScreensEnum.BookDetailsScreen.name}/dadad")

    }

    Card(shape = RoundedCornerShape(8.dp), elevation = CardDefaults.cardElevation(8.dp), modifier = newModifier) {

        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(imageData.volumeInfo.imageLinks.thumbnail)
                .crossfade(true)
                .build(), contentDescription = null, modifier = Modifier.fillMaxWidth(),
            placeholder = painterResource(id = R.drawable.loading_img),
            contentScale = ContentScale.FillWidth,

        )
    }


}