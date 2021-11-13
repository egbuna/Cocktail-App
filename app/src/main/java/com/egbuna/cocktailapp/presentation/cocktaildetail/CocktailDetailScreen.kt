package com.egbuna.cocktailapp.presentation.cocktaildetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import kotlinx.coroutines.NonDisposableHandle.parent

@Composable
fun CocktailDetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    cocktailDetail: GetCocktailViewModel = hiltViewModel()
) {

    val state = cocktailDetail.state.value

    if (state.loading) {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            CircularProgressIndicator(modifier = Modifier)
        }
    }

    if (state.cocktail != null) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            Box() {
                Image(
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    painter = rememberImagePainter(state.cocktail?.drinkThumb), contentDescription = "Cocktail image"
                )
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = {
                                  navController.popBackStack()
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "back",
                            tint = Color.White
                        )
                    }
                    IconButton(
                        onClick = {

                        },
                    ) {
                        Icon(
                            imageVector = if (state.cocktail?.favourited == true) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "back",
                            tint = Color.White
                        )
                    }
                }
            }

            Text(text = state.cocktail?.drink ?: "",
            color = Color.White, style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp)
            )

            Text(text = "Instructions",
                color = Color.White, style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(start = 16.dp, top = 32.dp)
            )

            Text(text = state.cocktail?.instructions ?: "",
                color = Color.White, style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(start = 16.dp, top = 6.dp, end = 16.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Ingredients",
                        color = Color.White, style = MaterialTheme.typography.h5,
                        textAlign = TextAlign.Center
                    )

                    state.cocktail?.ingredients?.forEach {
                        if (it.isNullOrEmpty().not()) {
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "$it", color = Color.White)
                        }
                    }
                }

                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "Measure",
                        color = Color.White, style = MaterialTheme.typography.h5,
                        textAlign = TextAlign.Center
                    )

                    state.cocktail?.measure?.forEach { measure ->
                        if (measure.isNullOrEmpty().not()) {
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "$measure", color = Color.White)
                        }
                    }
                }
            }
        }
    }

    if (state.error?.isNotBlank() == true) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {

            Text(
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth(),
                text = state.error,
                color = MaterialTheme.colors.error
            )
        }
    }
}