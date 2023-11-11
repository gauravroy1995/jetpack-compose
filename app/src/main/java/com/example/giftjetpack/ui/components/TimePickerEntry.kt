package com.example.giftjetpack.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@ExperimentalMaterial3Api
@Composable
fun TimePickerEntry(datePickerState: DatePickerState) {


    DatePicker(
        state = datePickerState,
        title = null,
        headline=null,
        modifier = Modifier
            .padding(16.dp),
        showModeToggle = false,
    )


}