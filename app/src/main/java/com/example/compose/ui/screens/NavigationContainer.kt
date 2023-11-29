package com.example.compose.ui.screens

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.compose.R
import com.example.compose.network.EachBookClass
import com.example.compose.ui.MarsPhotosApp
import com.example.compose.ui.viewmodels.SharedViewModel


enum class QuizClassScreensEnum(@StringRes val title: Int) {
    MarsPhotosApp(title = (R.string.booklist)),
    BookDetailsScreen(title = (R.string.book_details))
}

val SharedUserViewModelComposition = compositionLocalOf<SharedViewModel> { error("No UserViewModel found!") }


/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavTopBar(
    currentScreen: QuizClassScreensEnum,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {

    @Composable
    fun getTitle(): String {


        return stringResource(currentScreen.title)
    }

    TopAppBar(
        title = { Text(text = getTitle()) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack && currentScreen != QuizClassScreensEnum.MarsPhotosApp) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizNavigation(
    navController: NavHostController = rememberNavController()
) {

    val backStackEntry by navController.currentBackStackEntryAsState()

    // shared view model
    val sharedViewModel:SharedViewModel = viewModel()





    val currentScreen = run {
        val currentRoute = backStackEntry?.destination?.route ?: QuizClassScreensEnum.MarsPhotosApp.name

        if (currentRoute.startsWith(QuizClassScreensEnum.BookDetailsScreen.name)) {
            QuizClassScreensEnum.BookDetailsScreen
        } else {
            QuizClassScreensEnum.valueOf(currentRoute)
        }
    }

    CompositionLocalProvider(SharedUserViewModelComposition provides sharedViewModel) {


        Scaffold(
            topBar = {
                NavTopBar(
                    currentScreen = currentScreen,
                    canNavigateBack = navController.previousBackStackEntry != null,
                    navigateUp = { navController.navigateUp() }
                )
            }
        ) { innerPadding ->

            NavHost(
                navController = navController,
                startDestination = QuizClassScreensEnum.MarsPhotosApp.name,
                modifier = Modifier.padding(innerPadding)
            ) {

                composable(route = QuizClassScreensEnum.MarsPhotosApp.name) {
                    MarsPhotosApp(
                        navController = navController,
                    )
                }

                composable(
                    route = "${QuizClassScreensEnum.BookDetailsScreen.name}/{book}",
                    arguments = listOf(navArgument("book") {
                        type = NavType.StringType
                    })
                ) {
                    val book = backStackEntry?.arguments?.getString("book") ?: "booksss"


                    BookDetailsScreen(book, )
                }
            }
        }
    }


}