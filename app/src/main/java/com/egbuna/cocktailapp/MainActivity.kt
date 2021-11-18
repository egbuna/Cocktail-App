package com.egbuna.cocktailapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
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

@ExperimentalAnimationApi
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
                val lazyListState = rememberLazyListState()

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
                        },
                        lazyListState = lazyListState
                    )
                }) {
                    Navigation(navController = navController, lazyListState = lazyListState)
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun Navigation(navController: NavHostController,
               lazyListState: LazyListState) {
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(Screen.HomeScreen.route) {
            CocktailListScreen(navController = navController, lazyListState = lazyListState)
        }

        composable(Screen.FavouriteScreen.route) {
            FavouriteScreen(navController = navController, lazyListState = lazyListState)
        }

        composable(Screen.DetailScreen.route + "/{cocktailId}") {
            CocktailDetailScreen(navController = navController)
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit,
    lazyListState: LazyListState
) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    val state = lazyListState.isScrollInProgress

    if (backStackEntry.value?.destination?.route == Screen.HomeScreen.route || backStackEntry.value?.destination?.route == Screen.FavouriteScreen.route) {

        BottomNavVisibility {
            AnimatedVisibility(visible = state.not(), enter = fadeIn(), exit = fadeOut(), modifier = Modifier.background(color = Color(0xFF342F2D))) {
                BottomNavigation(
                    modifier = modifier
                        .fillMaxWidth().background(shape = RoundedCornerShape(8.dp), color = Color(0xFF342F2D)),
                    elevation = 5.dp
                ) {
                    items.forEach {

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
}

@Composable
fun BottomNavVisibility(content: @Composable () -> Unit) {
    Surface(Modifier.padding(bottom = 16.dp, start = 30.dp, end = 30.dp),
        RoundedCornerShape(8.dp)) {
        content()
    }
}