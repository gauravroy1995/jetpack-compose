package com.example.geministarter.history

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.geministarter.R
import com.example.geministarter.ScreenNames
import com.example.geministarter.database.PhotoSearchEntity
import com.example.geministarter.viewmodels.GenerativeViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryList(navController:NavController,sharedHistorySharedViewModel:HistorySharedViewModel, viewModel: HistoryViewModel = viewModel(factory = GenerativeViewModelFactory)) {

    val recipesText = viewModel.recipesState.collectAsState()

    val historyItems: List<PhotoSearchEntity> = recipesText.value





    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("History") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back_arrow),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            items(historyItems) { recipe ->

                val uriString = recipe.uriString;
                val uriFromString = Uri.parse(uriString)


                Log.d("HistoryList", "HistoryList: $uriString $uriFromString")
                val subRecipe = recipe.answer.substring(0, minOf(recipe.answer.length, 30))
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        // Circular Image on the left
                        AsyncImage(
                            model = uriString,
                            contentDescription = null,
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                        )

                        // Recipe details in the middle
                        Column(
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .weight(1f)
                        ) {
                            Text(
                                text = subRecipe,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )

                            // "Read More" at the bottom right
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.End)
                            ) {
                                Text(
                                    text = "Read More",
                                    modifier = Modifier.padding(6.dp).clickable {
                                        sharedHistorySharedViewModel.selectHistory(recipe)
                                        navController.navigate(ScreenNames.HISTORY_DETAIL_SCREEN.name)
                                    },
                                    color = Color.Gray,
                                    fontStyle = FontStyle.Italic
                                )
                            }
                        }

                        // Delete button on the right
                        IconButton(
                            onClick = { viewModel.deleteItem(recipe) },
                            modifier = Modifier.align(Alignment.CenterVertically)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_delete_24),
                                // Replace with your delete icon
                                contentDescription = null,

                                )
                        }
                    }
                }

            }
        }
    }




}


