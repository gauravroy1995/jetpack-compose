package com.example.compose.ui.screen


import android.content.Context
import android.content.Intent
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose.R
import com.example.compose.ui.viewmodel.VegetableViewModel


enum class VegetableScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Entree(title = R.string.new_cupcake_order),
    Sides(title = R.string.choose_flavor),
    Accompany(title = R.string.choose_pickup_date),
    Checkout(title = R.string.order_summary),
    Vegetables(title = R.string.vegetables)
}


/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VegetableAppBar(
    currentScreen: VegetableScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {


    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VegetableApp(
    viewModel: VegetableViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavHostController = rememberNavController()
) {

    // to get the current screen from the back stack
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = VegetableScreen.valueOf(
        backStackEntry?.destination?.route ?: VegetableScreen.Start.name
    )

    Scaffold(
        topBar = {
            VegetableAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = VegetableScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = VegetableScreen.Start.name) {
                StartScreen(
                    onClick = { navController.navigate(VegetableScreen.Entree.name) }
                )

            }

            composable(route = VegetableScreen.Vegetables.name) {
                VegetableScreen(
                    viewModel = viewModel,
                    onNextClick = { navController.navigate(VegetableScreen.Accompany.name) } ,
                    onCancelClick = {
                        cancelOrderAndNavigateToStart(
                            viewModel,
                            navController
                        )
                    }
                )

            }

            composable(route = VegetableScreen.Entree.name) {
                Entree(
                    viewModel = viewModel,
                    onNextClick = {  navController.navigate(VegetableScreen.Vegetables.name) },
                    onCancelClick = { cancelOrderAndNavigateToStart(viewModel, navController) }
                )

            }

            composable(route = VegetableScreen.Accompany.name) {
                FinalAccompany(
                    viewModel = viewModel,
                    onNextClick = {  navController.navigate(VegetableScreen.Checkout.name) },
                    onCancelClick = { cancelOrderAndNavigateToStart(viewModel, navController) }
                )

            }

            composable(route = VegetableScreen.Checkout.name) {
                Checkout(
                    viewModel = viewModel,
                )

            }


        }

    }
}


private fun shareOrder(context: Context, subject: String, summary: String) {
    val intent = Intent(Intent.ACTION_SEND)
        .apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, summary)
        }

    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.new_cupcake_order)
        )
    )

}


private fun cancelOrderAndNavigateToStart(
    viewModel: VegetableViewModel,
    navController: NavHostController
) {
    viewModel.resetOrder()
    navController.popBackStack(VegetableScreen.Start.name, inclusive = false)
}