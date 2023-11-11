package com.example.giftjetpack.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ImageRender(imageId:Int?=null){

    if(imageId==null){
        return
    }

    var imageId = imageId?.let { painterResource(id = it) }

    if (imageId != null) {
        Image(painter = imageId, contentDescription = null,contentScale= ContentScale.Fit,  modifier = Modifier.height(200.dp).width(200.dp))
    }
}