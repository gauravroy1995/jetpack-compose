package com.example.compose.ui.components

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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.compose.R
import com.example.compose.network.AmphibiansDataClass


@Composable
fun AmphibianCard(imageData: AmphibiansDataClass) {

    val ( name,type,description,imgSrc) = imageData

    val finalText = "$name ($type)"

    Card(shape = RoundedCornerShape(8.dp), elevation = CardDefaults.cardElevation(8.dp), modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp)) {
        Text(text =finalText, modifier = Modifier.padding(start = 12.dp, bottom = 12.dp, top = 12.dp))
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(imageData.imgSrc)
                .crossfade(true)
                .build(), contentDescription = null, modifier = Modifier.fillMaxWidth(),
            placeholder = painterResource(id = R.drawable.loading_img),
            contentScale = ContentScale.FillWidth
        )
        Text(text =description, textAlign = TextAlign.Justify, modifier = Modifier.padding(8.dp))
    }


}