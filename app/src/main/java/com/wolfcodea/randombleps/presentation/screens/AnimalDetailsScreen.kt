@file:OptIn(ExperimentalMaterial3Api::class)

package com.wolfcodea.randombleps.presentation.screens

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.wolfcodea.randombleps.core.colors.AppColors
import com.wolfcodea.randombleps.core.navigation.AppScreens
import com.wolfcodea.randombleps.data.models.AnimalsRandomViewModel


@Composable
fun AnimalDetailsScreen(navController: NavController, animalName: String?) {
    val viewModel = hiltViewModel<AnimalsRandomViewModel>()
    val data = viewModel.data.value

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current).data(data.data?.url).size(
            Size.ORIGINAL
        ).build()
    )



    if (painter.state is AsyncImagePainter.State.Loading) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.size(60.dp)) {
            LoadingAnimation()
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.getAnimalsRandom(animalName!!)
    }

    if (data.loading == true) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = AppColors.mLightPurple,
                        titleContentColor = AppColors.mOffWhite,
                        navigationIconContentColor = AppColors.mOffWhite,
                        actionIconContentColor = MaterialTheme.colorScheme.onSecondary
                    ),
                    navigationIcon = {
                        IconButton(onClick = { navController.navigate(route = AppScreens.HomeScreen.name) }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                    title = {
                        Text("Details for ${data.data?.tags?.first()}")
                    }
                )
            },
        ) {paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxWidth().padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    contentAlignment = Alignment.Center, modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.8f)
                        .padding(10.dp)
                ) {
                    Image(painter = painter, contentDescription = "Image of animal")
                }
                Box(contentAlignment = Alignment.Center) {
                    TextButton(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AppColors.mLightPurple,
                            contentColor = AppColors.mOffWhite
                        ),
                        onClick = {
                            navController.navigate(route = AppScreens.AnimalDetailsScreen.name + "/${data.data?.tags?.first()}")
                        }) {
                        Text(text = "Another")
                    }
                }
                Text(
                    text = "If after browsing you see the same photo repeated, our database has not yet been populated with new images.",
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}

@Composable
fun LoadingAnimation() {
    val animation = rememberInfiniteTransition(label = "")
    val progress by animation.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Restart,
        ), label = ""
    )

    Box(
        modifier = Modifier
            .size(60.dp)
            .scale(progress)
            .alpha(1f - progress)
            .border(
                5.dp,
                color = Color.Black,
                shape = CircleShape
            )
    )
}