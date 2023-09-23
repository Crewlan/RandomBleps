package com.wolfcodea.randombleps.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wolfcodea.randombleps.data.models.AnimalsTagViewModel


@Composable
fun AnimalsHome(viewModel: AnimalsTagViewModel){
    val animalsTag = viewModel.data.value.data?.toMutableList()

    if(viewModel.data.value.loading == true){
        Box(modifier = Modifier.fillMaxSize()){
            CircularProgressIndicator()
        }
    }else{
        Log.d("TAG", "AnimalsHome: $animalsTag")
        BuildLazyAnimals(animalsTag)
    }
}

@Composable
fun BuildLazyAnimals(animals:  List<String>? ){
    LazyColumn{
        items(items = animals!!){
            animal -> Text(text = "$animal")
        }
    }
}