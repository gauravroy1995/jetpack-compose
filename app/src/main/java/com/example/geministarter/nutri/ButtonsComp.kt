package com.example.geministarter.nutri


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ButtonComp(onClickB: () -> Unit, iconId: Int,text:String) {

    Row(
        modifier = Modifier
            .padding(all = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClickB() }
        ,
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {


        IconButton(
          onClick = {
              onClickB()
          }
        ) {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = text,
                tint = Color.White
            )
        }

        Text(
            text =text,
            color = Color.White,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}