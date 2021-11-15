package com.egbuna.cocktailapp.presentation.cocktaillist.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.egbuna.cocktailapp.domain.model.Cocktail
import com.egbuna.cocktailapp.presentation.Screen
import com.egbuna.cocktailapp.presentation.cocktaillist.CocktailListViewModel
import com.egbuna.cocktailapp.presentation.favourite.FavouriteCocktailViewModel

@ExperimentalFoundationApi
@Composable
fun CocktailListScreen(
    navController: NavController,
    viewModel: CocktailListViewModel = hiltViewModel(),
    favouriteCocktailViewModel: FavouriteCocktailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val searchState = viewModel.searchState.value

    val listState = rememberLazyListState()

    if (state.success.isNotEmpty()) {

        Column() {
            Box() {
                TextField(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 8.dp, end = 8.dp)
                    ,value = searchState.searchWord,
                    trailingIcon = {
                        Icon(imageVector = if (searchState.isHintVisible) Icons.Outlined.Search else Icons.Outlined.Clear, contentDescription = "Search",
                        modifier = Modifier.clickable {
                            viewModel.onTextChange("")
                        })
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color(0xFF211329),
                        backgroundColor = Color.LightGray,
                        cursorColor = Color.White,
                        trailingIconColor = Color(0xFF211329),
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    ),
                    onValueChange = { text ->
                        viewModel.onTextChange(text)
                    })

                if (searchState.isHintVisible)
                    Text(text = "e.g Margarita",
                    modifier = Modifier
                        .padding(start = 22.dp, top = 25.dp),
                    style = MaterialTheme.typography.body1,
                        color = Color.DarkGray
                    )
            }

            LazyVerticalGrid(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 8.dp, end = 4.dp, top = 8.dp),
                cells = GridCells.Fixed(2)
            ) {
                items(state.success) { item: Cocktail ->
                    CocktailListItem(
                        onClick = { navController.navigate(Screen.DetailScreen.route + "/${item.id}") },
                        cocktail = item,
                        onFavouriteItemClick = {
                            favouriteCocktailViewModel.saveCocktail(it)
                        })
                }
            }
        }
    } else if (state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(modifier = Modifier)
        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                text = state.error.orEmpty(),
                color = MaterialTheme.colors.error
            )
        }
    }

}