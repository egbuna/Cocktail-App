package com.egbuna.cocktailapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.egbuna.cocktailapp.common.BottomNavItem
import com.egbuna.cocktailapp.presentation.Screen
import com.egbuna.cocktailapp.presentation.cocktaildetail.CocktailDetailScreen
import com.egbuna.cocktailapp.presentation.favourite.FavouriteScreen
import com.egbuna.cocktailapp.presentation.cocktaillist.component.CocktailListScreen
import com.egbuna.cocktailapp.ui.theme.CocktailAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            systemUiController.setStatusBarColor(Color(0xFF211329))

            CocktailAppTheme {
                val navController = rememberNavController()
                Scaffold(backgroundColor = Color(0xFF211329),bottomBar = {
                    BottomNavigationBar(
                        items = listOf(
                            BottomNavItem(
                                name = "Home",
                                icon = Icons.Default.Home,
                                route = "home"
                            ),
                            BottomNavItem(
                                name = "Favourite",
                                icon = Icons.Default.Favorite,
                                route = "favourite"
                            ),
                        ),
                        navController = navController,
                        onItemClick = {
                            navController.navigate(it.route)
                        }
                    )
                }) {
                    Navigation(navController = navController)
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(Screen.HomeScreen.route) {
            CocktailListScreen(navController = navController)
        }

        composable(Screen.FavouriteScreen.route) {
            FavouriteScreen(navController = navController)
        }

        composable(Screen.DetailScreen.route + "/{cocktailId}") {
            CocktailDetailScreen(navController = navController)
        }
    }
}

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    if (backStackEntry.value?.destination?.route == Screen.HomeScreen.route || backStackEntry.value?.destination?.route == Screen.FavouriteScreen.route) {
        Card(shape = RoundedCornerShape(8.dp), modifier = Modifier.padding(bottom = 16.dp, start = 30.dp, end = 30.dp)) {
            BottomNavigation(
                modifier = modifier
                    .fillMaxWidth(),
                backgroundColor = Color(0xFF342F2D),
                elevation = 5.dp
            ) {
                items.forEach {
                    val selected = it.route == backStackEntry.value?.destination?.route

                    BottomNavigationItem(
                        selected = it.route == navController.currentDestination?.route,
                        onClick = { onItemClick.invoke(it) },
                        icon = {
                            Icon(imageVector = it.icon, contentDescription = it.name)
                        },
                        unselectedContentColor = Color.Gray
                    )
                }
            }
        }
    }
}