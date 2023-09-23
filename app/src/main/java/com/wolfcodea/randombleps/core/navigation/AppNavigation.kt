package com.wolfcodea.randombleps.core.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.wolfcodea.randombleps.data.models.AnimalsTagViewModel
import com.wolfcodea.randombleps.presentation.screens.AnimalDetailsScreen
import com.wolfcodea.randombleps.presentation.screens.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.HomeScreen.name) {
        composable(AppScreens.HomeScreen.name) {
            val animalsTagViewModel = hiltViewModel<AnimalsTagViewModel>()
            HomeScreen(viewModel = animalsTagViewModel, navController = navController, )
        }

        composable(
            AppScreens.AnimalDetailsScreen.name + "/{animalName}",
            arguments = listOf(navArgument(name = "animalName") { type = NavType.StringType })
        ) {
                backStackEntry  ->
            AnimalDetailsScreen(navController = navController,  backStackEntry.arguments?.getString("animalName"))

        }
    }
}