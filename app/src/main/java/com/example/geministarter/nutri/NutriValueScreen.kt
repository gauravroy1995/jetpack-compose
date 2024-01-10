package com.example.geministarter.nutri

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.request.SuccessResult
import coil.size.Precision
import com.example.geministarter.BuildConfig
import com.example.geministarter.R
import com.example.geministarter.util.UriSaver
import com.example.geministarter.viewmodels.GenerativeViewModelFactory
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects

fun Context.createImageFile(): File {
    // Create an image file name
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    val image = File.createTempFile(
        imageFileName, /* prefix */
        ".jpg", /* suffix */
        externalCacheDir      /* directory */
    )
    return image
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun NutriReasoningScreen(
    navController: NavController,
    viewModel: NutriViewModel = viewModel(factory = GenerativeViewModelFactory)
) {
    val NutriUiState by viewModel.uiState.collectAsState()


    val coroutineScope = rememberCoroutineScope()
    val imageRequestBuilder = ImageRequest.Builder(LocalContext.current)
    val imageLoader = ImageLoader.Builder(LocalContext.current).build()




Scaffold(
    topBar={

            TopAppBar(
                title = { Text("Get nutritional details") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back_arrow),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }

            )

    }
) {
    Column(modifier = Modifier.padding(it)){
        NutriComp(
            uiState = NutriUiState,
            onReasonClicked = { selectedItems ->
                coroutineScope.launch {
                    val bitmaps = selectedItems.mapNotNull {
                        val imageRequest = imageRequestBuilder
                            .data(it)
                            // Scale the image down to 768px for faster uploads
                            .size(size = 768)
                            .precision(Precision.EXACT)
                            .build()
                        try {
                            val result = imageLoader.execute(imageRequest)
                            if (result is SuccessResult) {
                                return@mapNotNull (result.drawable as BitmapDrawable).bitmap
                            } else {
                                return@mapNotNull null
                            }
                        } catch (e: Exception) {
                            return@mapNotNull null
                        }
                    }
                    viewModel.reason(bitmaps,selectedItems[0])
                }
            }
        )
    }

}

}

@Composable
fun NutriComp(
    uiState: NutriUiState = NutriUiState.Loading,
    onReasonClicked: (List<Uri>) -> Unit = { _ -> }
) {
    var userQuestion by rememberSaveable { mutableStateOf("") }
    val imageUris = rememberSaveable(saver = UriSaver()) { mutableStateListOf() }
    val context = LocalContext.current


    val pickMedia = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { imageUri ->

        imageUri?.let {
            val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
            context.contentResolver.takePersistableUriPermission(imageUri, flag)
            imageUris.add(it)
        }
    }


    val file = context.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        BuildConfig.APPLICATION_ID + ".provider", file
    )

    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
            if (isSuccess) {
                imageUris.add(uri)
            }
        }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            cameraLauncher.launch(uri)
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }




    Column(
        modifier = Modifier
            .padding(all = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {

                Column {

                    ButtonComp(
                        onClickB = {
                            pickMedia.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        }, R.drawable.gallery_icon,
                        "Gallery"
                    )

                    ButtonComp(onClickB = {
                        val permissionCheckResult =
                            ContextCompat.checkSelfPermission(
                                context,
                                Manifest.permission.CAMERA
                            )
                        if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                            cameraLauncher.launch(uri)
                        } else {
                            // Request a permission
                            permissionLauncher.launch(Manifest.permission.CAMERA)
                        }
                    }, iconId = R.drawable.camera_icon,"Camera")
                }
                // Pick from gallery icon





                    TextButton(
                        onClick = {
                            onReasonClicked(imageUris.toList())
                        },
                        modifier = Modifier.padding(all = 8.dp),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.textButtonColors(containerColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Text(
                            stringResource(R.string.action_go),
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,

                        )
                    }

            }
            // Display selected images
            LazyRow(
                modifier = Modifier.padding(all = 8.dp)
            ) {
                items(imageUris) { imageUri ->
                    AsyncImage(
                        model = imageUri,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(4.dp)
                            .requiredSize(72.dp)
                    )
                }
            }
        }
        // Show loading, success, or error states
        when (uiState) {
            NutriUiState.Initial -> {
                // Nothing is shown
            }

            NutriUiState.Loading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(all = 8.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    CircularProgressIndicator()
                }
            }

            is NutriUiState.Success -> {
                NutriSuccessComp(outputText = uiState.outputText,imageUris = imageUris.toList())
            }

            is NutriUiState.Error -> {
                Card(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .fillMaxWidth(),
                    shape = MaterialTheme.shapes.large,
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    )
                ) {
                    Text(
                        text = uiState.errorMessage,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(all = 16.dp)
                    )
                }
            }
        }
    }
}