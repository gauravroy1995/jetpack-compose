package com.example.geministarter.nutri

import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import coil.size.Precision
import com.example.geministarter.viewmodels.GenerativeViewModelFactory
import kotlinx.coroutines.launch

@Composable
fun NutriSuccessComp(outputText:String, imageUris:List<Uri> ,viewModel: NutriViewModel = viewModel(factory = GenerativeViewModelFactory)  ) {

    val coroutineScope = rememberCoroutineScope()
    val imageRequestBuilder = ImageRequest.Builder(LocalContext.current)
    val imageLoader = ImageLoader.Builder(LocalContext.current).build()



    Column(modifier = Modifier.fillMaxSize().padding(top=20.dp)) {
        Button(onClick = {
            coroutineScope.launch {
                val bitmaps = imageUris.mapNotNull {
                    val imageRequest = imageRequestBuilder
                        .data(it)
                        // Scale the image down to 768px for faster uploads
                        .size(size = 768)
                        .precision(Precision.EXACT)
                        .build()
                    try {
                        val result = imageLoader.execute(imageRequest)
                        if (result is SuccessResult) {
                            return@mapNotNull (result.drawable as BitmapDrawable).bitmap
                        } else {
                            return@mapNotNull null
                        }
                    } catch (e: Exception) {
                        return@mapNotNull null
                    }
                }
                viewModel.getRecipes(bitmaps)
            }
        },modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(text = "Get recipes")
        }
        RecipesField()
        Card(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onSecondaryContainer
            )
        ) {

            val context = LocalContext.current



            Row(
                modifier = Modifier
                    .padding(all = 16.dp)
                    .fillMaxWidth()
            ) {
                Icon(
                    Icons.Outlined.Person,
                    contentDescription = "Person Icon",
                    tint = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier
                        .requiredSize(36.dp)
                        .drawBehind {
                            drawCircle(color = Color.White)
                        }
                )
                SelectionContainer{
                    Text(

                        text = outputText,
                        color = MaterialTheme.colorScheme.onSecondary,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .fillMaxWidth()
                    )
                }

            }
        }
    }


}