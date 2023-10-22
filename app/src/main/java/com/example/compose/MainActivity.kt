@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.compose


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.compose.ui.theme.ComposeTheme




data class MyItem(
    val title: String,
    val description: String,
    val imageUrl: String
)


val myMutableObjectList: MutableList<MyItem> = mutableListOf(
    MyItem(
        "Title 1",
        "Description 1",
        "https://images.unsplash.com/photo-1607604276583-eef5d076aa5f?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8YW5pbWV8ZW58MHx8MHx8fDA%3D&auto=format&fit=crop&w=800&q=60"
    ),
    MyItem(
        "Title 2",
        "Description 2",
        "https://images.unsplash.com/photo-1560972550-aba3456b5564?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTh8fGFuaW1lfGVufDB8fDB8fHww&auto=format&fit=crop&w=800&q=60"
    ),
    MyItem(
        "Title 3",
        "Description 3",
        "https://plus.unsplash.com/premium_photo-1682124752476-40db22034a58?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8YW5pbWV8ZW58MHx8MHx8fDA%3D&auto=format&fit=crop&w=800&q=60"
    ),
    MyItem(
        "Title 3",
        "Description 3",
        "https://plus.unsplash.com/premium_photo-1682124752476-40db22034a58?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8YW5pbWV8ZW58MHx8MHx8fDA%3D&auto=format&fit=crop&w=800&q=60"
    ),
    MyItem(
        "Title 3",
        "Description 3",
        "https://plus.unsplash.com/premium_photo-1682124752476-40db22034a58?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8YW5pbWV8ZW58MHx8MHx8fDA%3D&auto=format&fit=crop&w=800&q=60"
    ),
    MyItem(
        "Title 3",
        "Description 3",
        "https://plus.unsplash.com/premium_photo-1682124752476-40db22034a58?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8YW5pbWV8ZW58MHx8MHx8fDA%3D&auto=format&fit=crop&w=800&q=60"
    ),
    MyItem(
        "Title 3",
        "Description 3",
        "https://plus.unsplash.com/premium_photo-1682124752476-40db22034a58?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8YW5pbWV8ZW58MHx8MHx8fDA%3D&auto=format&fit=crop&w=800&q=60"
    ),
    MyItem(
        "Title 3",
        "Description 3",
        "https://plus.unsplash.com/premium_photo-1682124752476-40db22034a58?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8YW5pbWV8ZW58MHx8MHx8fDA%3D&auto=format&fit=crop&w=800&q=60"
    ),
    MyItem(
        "Title 3",
        "Description 3",
        "https://plus.unsplash.com/premium_photo-1682124752476-40db22034a58?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8YW5pbWV8ZW58MHx8MHx8fDA%3D&auto=format&fit=crop&w=800&q=60"
    )
)


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),

                ) {
                    RenderGreeting()
                }
            }
        }
    }
}


@Composable
fun RenderName(title: String, modifier: Modifier = Modifier){
    Column(modifier = modifier.padding(start = 10.dp)) {

        Text(text = title)
        Text(text = "Dog name")
    }
}

@Composable
fun WoofTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.image_size))
                        .padding(dimensionResource(R.dimen.padding_small)),
                    painter = painterResource(R.drawable.img3),
                    contentDescription = null
                )
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
        modifier = modifier
    )
}

@Composable
private fun RenderCardButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){

    var imageName:ImageVector = if(!expanded) Icons.Filled.ExpandMore else Icons.Filled.ExpandLess

    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
    Icon(imageVector = imageName,contentDescription = null, tint = MaterialTheme.colorScheme.secondary)
    }
}




@Composable
fun RenderList(cardData:MyItem,modifier: Modifier = Modifier){

    var expanded by remember { mutableStateOf(false) }
    val color by animateColorAsState(
        targetValue = if (expanded) MaterialTheme.colorScheme.tertiaryContainer
        else MaterialTheme.colorScheme.primaryContainer,
        label = "",
    )
    val image = cardData.imageUrl
    val title = cardData.title



    Card(modifier = modifier
        .fillMaxWidth()
        .padding(vertical = 20.dp, horizontal = 20.dp)
        .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 2.dp))){
        Column(modifier = modifier.background(color).animateContentSize(animationSpec = spring(dampingRatio = Spring.DampingRatioNoBouncy, stiffness = Spring.StiffnessMedium))) {
            Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically){
                AsyncImage(model = image, contentDescription = title, modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
                    .clip(RoundedCornerShape(30.dp)), contentScale = ContentScale.FillBounds)
                RenderName(title = title)
                Spacer(modifier = Modifier.weight(1f))
                RenderCardButton(expanded = expanded, onClick = {expanded  = !expanded})
            }
            if(expanded){
                RenderBottomPart(expanded,modifier)
            }
        }


    }

}
@Composable
fun RenderBottomPart(expanded: Boolean, modifier: Modifier = Modifier){
    Column(modifier = Modifier.padding(10.dp)) {
        Text(text = "Bottom Part")
    }
   
}


@Composable
fun RenderGreeting(modifier: Modifier = Modifier){

    Scaffold(topBar = { WoofTopAppBar() }) {
        LazyColumn(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally , modifier = modifier,contentPadding = it ){
            items(myMutableObjectList.size){
                    index ->
                RenderList(cardData = myMutableObjectList[index])        }
        }


    }

    }






@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ComposeTheme(darkTheme = true) {
        RenderGreeting()
    }
}