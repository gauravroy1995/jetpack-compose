package com.example.geministarter.history

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.geministarter.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryDetails(
    navController: NavController,
    sharedHistorySharedViewModel: HistorySharedViewModel
) {

    val selectedHistory = sharedHistorySharedViewModel.selectedHistory.value ?: return

    val context = LocalContext.current

    val image = selectedHistory.uriString
    val text = selectedHistory.answer

    val uri = Uri.parse(image)


    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, "hdjahdjahdajd")
        putExtra(Intent.EXTRA_STREAM, uri)
        type = "*/*"
    }.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("History Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back_arrow),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        context.startActivity(sendIntent)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_share_24),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }

            )
        }
    ) {

        Column(
            modifier = Modifier
                .padding(it)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = image, contentDescription = null, modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
            Row {
                Text(
                    text = text,
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(12.dp),
                    fontSize = 16.sp
                )
            }


        }
    }
}