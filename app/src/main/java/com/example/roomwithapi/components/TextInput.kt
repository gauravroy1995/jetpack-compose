package com.example.roomwithapi.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TextInputField(currentText:String,onChangeText: (String) -> Unit) {
    var text by remember { mutableStateOf("") }





    OutlinedTextField(
        value = currentText.toString(),
        onValueChange = { it -> onChangeText(it) },
        label = { Text("Airport Name") },
        modifier = Modifier.padding(16.dp),
        singleLine = true
    )
}