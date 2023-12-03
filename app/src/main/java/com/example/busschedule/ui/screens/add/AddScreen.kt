package com.example.busschedule.ui.screens.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.busschedule.data.BusEntity
import com.example.busschedule.ui.screens.home.HomeViewModel
import com.example.busschedule.ui.viewmodel.AppViewModelProvider
import kotlinx.coroutines.launch
import java.util.Calendar

@Composable
fun AddScreen(contentPaddingValues:PaddingValues, navController: NavController, viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)){
    var stopName by remember { mutableStateOf("") }
    var selectedTime by remember { mutableStateOf(Calendar.getInstance()) }

    val additionalPadding = PaddingValues(horizontal = 24.dp)

    val coroutineScope = rememberCoroutineScope()


    val mod = Modifier.padding(horizontal = 20.dp)


    fun onSaveClick(){
        val item = BusEntity(
            stopName = stopName,
            arrivalTimeInMillis = selectedTime.timeInMillis.toInt()
        )

        coroutineScope.launch {
            viewModel.insertData(item)
            navController.navigateUp()
        }


    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPaddingValues)
            .composed { mod }
    ) {
        // Input for Stop Name
        OutlinedTextField(
            value = stopName,
            onValueChange = { stopName = it },
            label = { Text("Stop Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
        )

        // Time Selector for Station Drop Time
        TimePicker(
            selectedTime = selectedTime,
            onTimeSelected = { selectedTime = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
        )

        // Save Button
        Button(
            onClick = {
                onSaveClick()
//                onSave(stopName, selectedTime)
            },

        ) {
            Text("Save")
        }
    }
}

@Composable
fun TimePicker(
    selectedTime: Calendar,
    onTimeSelected: (Calendar) -> Unit,
    modifier: Modifier = Modifier
) {
    var timePickerState by remember { mutableStateOf(selectedTime) }

    // This is just a basic example, you might want to use a library for a more feature-rich time picker.

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Display the selected time
        Text(
            text = "${selectedTime.get(Calendar.HOUR_OF_DAY)}:${selectedTime.get(Calendar.MINUTE)}",
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp)
        )

        // Button to open a time picker dialog
        Button(onClick = {
            // Show your custom time picker or use a library for this purpose
            // In this example, a simple time picker is shown, which can be replaced with a more sophisticated solution
            // Note: The actual implementation of the time picker is not included here and depends on your specific requirements.
        }) {
            Text("Pick Time")
        }
    }
}
