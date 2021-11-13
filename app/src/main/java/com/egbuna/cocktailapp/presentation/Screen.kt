package com.egbuna.cocktailapp.presentation

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home")
    object FavouriteScreen: Screen("favourite")
    object DetailScreen: Screen("detail")
}
