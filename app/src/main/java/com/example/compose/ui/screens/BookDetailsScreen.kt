package com.example.compose.ui.screens

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
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
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.compose.R
import com.example.compose.database.EachBookEntity
import com.example.compose.network.EachBookClass
import com.example.compose.ui.theme.darkModeGradientColors
import com.example.compose.ui.theme.lightModeGradientColors
import kotlinx.serialization.json.Json

@Composable
fun BookDetailsScreen(book: String) {

    val bookObjectDirect =  SharedUserViewModelComposition.current.currentSetBook.value
    val bookObject: EachBookEntity? = remember {
        bookObjectDirect
    }



    Log.d(
        "BookDetailsScreen",
        "BookDetailsScreen: ${SharedUserViewModelComposition.current.currentSetBook}"
    )
    if (bookObject != null) {

            BookDetailsContent(bookObject = bookObject)


    }

}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
private fun BookDetailsContent(bookObject: EachBookEntity) {

    val visibleState = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = true
        }
    }

    val gradientColors: List<Color> = if (isSystemInDarkTheme()) {
        darkModeGradientColors
    } else {
        lightModeGradientColors
    }

    val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")
    val density = LocalDensity.current
    val animatedColor by infiniteTransition.animateColor(
        initialValue = Color(0xFF60DDAD),
        targetValue = Color(0xFF4285F4),
        animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
        label = "color"
    )

    AnimatedVisibility(visibleState = visibleState,
        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = gradientColors
                    )
                )
        ) {


            // Display Book Cover Image using Coil
            CoilImage(
                data = bookObject.thumbnail,
                contentDescription = null,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(8.dp))
                    .animateEnterExit(
                    // Slide in/out the inner box.
                    enter = slideInVertically(),
                    exit = slideOutVertically()
                )
            )

            // Display Book Title
            Text(
                text = bookObject.title,
                fontSize = 24.sp,
                color=animatedColor,
                modifier = Modifier.padding(vertical = 28.dp, horizontal = 16.dp),
            )

            // Display Authors
            AuthorsList(authors = bookObject.authors)

            // Placeholder for other book details
            // ...

            // Like and Visibility icons (for demonstration)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconWithText(icon = Icons.Default.ThumbUp, text = "120 Likes")
                IconWithText(icon = Icons.Default.Visibility, text = "320 Views")
            }
        }
    }
}


@Composable
private fun AuthorsList(authors: List<String>) {
    if (authors.isNotEmpty()) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
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
        model = data,

        contentDescription = null, modifier = Modifier
            .fillMaxWidth()
            .height(500.dp).padding(top = 20.dp, start = 20.dp, end = 20.dp).clip(shape = RoundedCornerShape(8.dp))
        ,
        placeholder = painterResource(id = R.drawable.loading_img),
        contentScale = ContentScale.FillWidth,

        )
}



