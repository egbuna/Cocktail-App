package com.egbuna.cocktailapp.presentation.favourite

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.egbuna.cocktailapp.domain.model.Cocktail
import com.egbuna.cocktailapp.presentation.Screen
import com.egbuna.cocktailapp.presentation.cocktaillist.component.CocktailListItem
import com.egbuna.cocktailapp.presentation.favourite.FavouriteCocktailViewModel

@ExperimentalFoundationApi
@Composable
fun FavouriteScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    favouriteCocktailViewModel: FavouriteCocktailViewModel = hiltViewModel(),
    lazyListState: LazyListState
) {

    val state = favouriteCocktailViewModel.state

    Column() {
        Text(
            modifier = Modifier.padding(start = 8.dp, top = 16.dp),
            text = "My favourites", style = MaterialTheme.typography.h3, color = Color.White
        )
        if (state.value.favouriteCocktails.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {

                Text(
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "You currently have no favourite item(s).",
                    color = MaterialTheme.colors.error
                )
            }
        }

        LazyVerticalGrid(
            state = lazyListState,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 8.dp, end = 8.dp, top = 16.dp),
            cells = GridCells.Fixed(2)
        ) {
            items(state.value.favouriteCocktails) { item: Cocktail ->
                CocktailListItem(
                    onClick = {
                        navController.navigate(Screen.DetailScreen.route + "/${item.id}")
                    },
                    cocktail = item,
                    onFavouriteItemClick = {

                    })

            }
        }
    }
}