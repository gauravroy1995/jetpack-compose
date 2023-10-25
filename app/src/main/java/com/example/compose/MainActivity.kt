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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.model.SuperHeroData
import com.example.compose.model.SuperheroDataClass
import com.example.compose.ui.theme.CabinBoldFont
import com.example.compose.ui.theme.CabinBoldRegular
import com.example.compose.ui.theme.ComposeTheme










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
fun RenderName(title: String,description: String, modifier: Modifier = Modifier){
    Column(modifier = modifier
        .padding(start = 10.dp)
        .fillMaxWidth()) {

        Text(text = title, fontFamily = CabinBoldFont)
        Text(text = description , fontFamily = CabinBoldRegular)
    }
}

@Composable
fun WoofTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text= "Superheroes",
                    style = MaterialTheme.typography.displayLarge,
                    fontFamily = CabinBoldFont
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
fun RenderList(cardData:SuperheroDataClass,modifier: Modifier = Modifier){

    var expanded by remember { mutableStateOf(false) }
    val color by animateColorAsState(
        targetValue = if (expanded) MaterialTheme.colorScheme.tertiaryContainer
        else MaterialTheme.colorScheme.primaryContainer,
        label = "",
    )
    val image = cardData.imageUrl
    val title = cardData.title
    val description = cardData.description

    val painterImg = painterResource(id = image)

    Card(modifier = modifier
        .fillMaxWidth()
        .padding(vertical = 20.dp, horizontal = 20.dp)
        .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 2.dp))){
        Column(modifier = modifier
            .background(color)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )) {
            Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically){
               Image(painter = painterImg, contentDescription = null,modifier = modifier.size(50.dp) )
                RenderName(title = title,description=description)
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
    val superHeroes = SuperHeroData().getSuperHeroes()
    Scaffold(topBar = { WoofTopAppBar() }) {
        LazyColumn(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally , modifier = modifier,contentPadding = it ){
            items(superHeroes.size){
                    index ->
                RenderList(cardData = superHeroes[index])        }
        }


    }

    }






@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ComposeTheme() {
        RenderGreeting()
    }
}