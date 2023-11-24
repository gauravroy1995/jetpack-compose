package com.example.compose.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.compose.R
import com.example.compose.network.EachBookClass
import kotlinx.serialization.json.Json

@Composable
fun BookDetailsScreen(book: String) {

    val bookObject: EachBookClass? = SharedUserViewModelComposition.current.currentSetBook.value

    Log.d(
        "BookDetailsScreen",
        "BookDetailsScreen: ${SharedUserViewModelComposition.current.currentSetBook}"
    )
    if (bookObject != null) {
        BookDetailsContent(bookObject = bookObject)
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BookDetailsContent(bookObject: EachBookClass) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF6200EA),
                        Color(0xFF3700B3),
                    )
                )
            )
    ) {


        // Display Book Cover Image using Coil
        CoilImage(
            data = bookObject.volumeInfo.imageLinks.thumbnail,
            contentDescription = null,
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(8.dp))
        )

        // Display Book Title
        Text(
            text = bookObject.volumeInfo.title,

            modifier = Modifier.padding(vertical = 8.dp)
        )

        // Display Authors
        AuthorsList(authors = bookObject.volumeInfo.authors)

        // Placeholder for other book details
        // ...

        // Like and Visibility icons (for demonstration)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconWithText(icon = Icons.Default.ThumbUp, text = "120 Likes")
            IconWithText(icon = Icons.Default.Visibility, text = "320 Views")
        }
    }
}


@Composable
private fun AuthorsList(authors: List<String>) {
    if (authors.isNotEmpty()) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.surface,
                modifier = Modifier
                    .size(20.dp)
                    .padding(end = 8.dp)
            )
            Text(
                text = authors[0],
            )
        }


    }
}

@Composable
private fun IconWithText(icon: ImageVector, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(20.dp)
                .padding(end = 4.dp)
        )
        Text(
            text = text,
            color = Color.White
        )
    }
}


@Composable
private fun CoilImage(
    data: String,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(data)
            .crossfade(true)
            .build(),
        contentDescription = null, modifier = Modifier.fillMaxWidth().height(500.dp),
        placeholder = painterResource(id = R.drawable.loading_img),
        contentScale = ContentScale.Fit,

        )
}



