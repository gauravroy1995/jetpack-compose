package com.example.animationcheck

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable

@Composable
fun FlatList(viewModel: MainViewModel) {

    val listOfNumbers = (1..30).toList()

    LazyColumn() {
        itemsIndexed(
            items = listOfNumbers,
            key = { _, index -> index },
            itemContent = { item, index ->
                CardWrap(item,viewModel)

            })
    }

}