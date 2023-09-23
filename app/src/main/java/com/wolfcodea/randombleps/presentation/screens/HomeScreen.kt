package com.wolfcodea.randombleps.presentation.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.wolfcodea.randombleps.data.models.AnimalsTagViewModel
import com.wolfcodea.randombleps.presentation.components.AnimalsHome


@Composable
fun HomeScreen (viewModel: AnimalsTagViewModel = hiltViewModel() ){
    AnimalsHome(viewModel)
}