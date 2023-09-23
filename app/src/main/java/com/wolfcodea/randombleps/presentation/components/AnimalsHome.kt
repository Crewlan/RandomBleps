package com.wolfcodea.randombleps.presentation.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wolfcodea.randombleps.core.colors.AppColors
import com.wolfcodea.randombleps.core.navigation.AppScreens
import com.wolfcodea.randombleps.data.models.AnimalsTagViewModel


@Composable
fun AnimalsHome(viewModel: AnimalsTagViewModel, navController: NavController) {
    val animalsTag = viewModel.data.value.data?.toMutableList()

    if (viewModel.data.value.loading == true) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Log.d("TAG", "AnimalsHome: $animalsTag")
        BuildLazyAnimals(animalsTag, navController)
    }
}

@Composable
fun BuildLazyAnimals(animals: List<String>?, navController: NavController) {
    LazyColumn {
        items(items = animals!!) {
            BuildAnimalTile(it, navController)
        }
    }
}


@Composable
fun BuildAnimalTile(animalName: String, navController: NavController) {
    Row(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
            .height(50.dp)
            .border(
                width = 4.dp, brush = Brush.linearGradient(
                    colors = listOf(AppColors.mLightPurple, AppColors.mLightPurple)
                ), shape = RoundedCornerShape(34.dp)
            )
            .clip(RoundedCornerShape(50.dp))
            .background(Color.Transparent)
            .clickable {navController.navigate(route = AppScreens.AnimalDetailsScreen.name+"/$animalName") },
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = animalName,
            modifier = Modifier.padding(horizontal = 20.dp),
            color = AppColors.mBlack
        )
        Icon(
            imageVector = Icons.Filled.ArrowForward,
            contentDescription = "See animal",
            modifier = Modifier.padding(horizontal = 20.dp),
            tint = AppColors.mBlack
        )
    }
}
