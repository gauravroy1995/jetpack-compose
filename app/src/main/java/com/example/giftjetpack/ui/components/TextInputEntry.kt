package com.example.giftjetpack.ui.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester

@Composable
fun TextInputEntry(textValue: String, onTextChange: (String) -> Unit, onNextPress: () -> Unit) {
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }


    TextField(
        value = textValue,
        singleLine = true,
        onValueChange = onTextChange,
        keyboardOptions = KeyboardOptions(imeAction = androidx.compose.ui.text.input.ImeAction.Done, autoCorrect = false),
        keyboardActions = KeyboardActions(
            onDone = {
                onNextPress()

            }
        ),
        modifier = Modifier
            .focusRequester(focusRequester)
    )
}