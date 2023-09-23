package com.wolfcodea.randombleps.presentation.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController


@Composable
fun AnimalDetailsScreen(navController: NavController, animalName: String?){
//    val viewModel = hiltViewModel<AnimalsRandomViewModel>()
//    val data = viewModel.data.value.data
//
//
//    LaunchedEffect(key1 = Unit ){
//        viewModel.getAnimalsRandom(animalName!!)
//    }
//
//    if(data != null){
//        Text(text = data.id.toString())
//    }
    Text(text = animalName!!)
}