package com.example.geministarter.nutri

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.geministarter.viewmodels.GenerativeViewModelFactory
import java.util.regex.Pattern

@Composable
fun RecipesField(viewModel: NutriViewModel = viewModel(factory = GenerativeViewModelFactory)) {
    val RecipeUiStates by viewModel.recipesState.collectAsState()
    val context = LocalContext.current



    when(val recState = RecipeUiStates){
        RecipeUiState.Initial -> {


        }
        is RecipeUiState.Loading -> {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(all = 8.dp).fillMaxWidth()

            ) {
                CircularProgressIndicator()
            }

        }
        is RecipeUiState.Success -> {

            val outputText = recState.outputText


            var arrTexts by remember { mutableStateOf(mutableListOf<String>()) }

            val annotatedString = buildAnnotatedString {
                val pattern = Pattern.compile("https?://\\S+")
                val matcher = pattern.matcher(outputText)

                while (matcher.find()) {
                    val start = matcher.start()
                    val end = matcher.end()


                    val uriExtracted = outputText.substring(start, end)
                    if (uriExtracted != "") {
                        arrTexts.add(outputText.substring(start, end))
                    }

                }
            }

            if (outputText != "" && arrTexts.size > 0) {


                Card(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .fillMaxWidth()
                        .height(200.dp),
                    shape = MaterialTheme.shapes.large,
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                ) {

                    LazyColumn(modifier = Modifier.fillMaxHeight()) {
                        items(arrTexts) { string ->

                            val newString = buildAnnotatedString {
                                addStringAnnotation(
                                    tag = "URL",
                                    annotation = string,
                                    start = 0,
                                    end = string.length
                                )
                                append(string)
                            }

                            ClickableText(
                                text = newString,
                                onClick = { offset ->
                                    val annotations = newString.getStringAnnotations(offset, offset)
                                    annotations.firstOrNull()?.let { annotation ->
                                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(annotation.item))
                                        context.startActivity(intent)
                                    }
                                },
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }

                }
            }
            Log.d("RecipesField", "RecipeUiState.Success")
        }
        is RecipeUiState.Error -> {
            Log.d("RecipesField", "RecipeUiState.Error")
        }

        else -> {
            Log.d("RecipesField", "RecipeUiState.Else")
        }
    }




}