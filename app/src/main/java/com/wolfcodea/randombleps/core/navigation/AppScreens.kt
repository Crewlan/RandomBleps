package com.wolfcodea.randombleps.core.navigation



enum class AppScreens {
    HomeScreen, AnimalDetailsScreen;
    companion object {
        fun fromRoute(route: String?) : AppScreens = when(route?.substringBefore("/")){
            HomeScreen.name -> HomeScreen
            AnimalDetailsScreen.name -> AnimalDetailsScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognize")
        }
    }
}