package com.egbuna.cocktailapp.presentation.cocktaillist.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.egbuna.cocktailapp.domain.model.Cocktail

@Composable
fun CocktailListItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onFavouriteItemClick: (Cocktail) -> Unit,
    cocktail: Cocktail
) {
    CocktailImageContainer() {
        Box(
            modifier = modifier
                .clickable {
                    onClick.invoke()
                }

        ) {
            Image(
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize(),
                painter = rememberImagePainter(cocktail.drinkThumb, builder = {
                    crossfade(true)
                }), contentDescription = "Cocktail image"
            )
            Text(text = cocktail.drink, color = Color.White, modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                .fillMaxWidth(),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis)
        }
    }
}

@Composable
fun CocktailImageContainer(content: @Composable () -> Unit) {
    Surface(
        Modifier
            .width(180.dp)
            .height(230.dp)
            .padding(top = 16.dp, end = 8.dp),
        RoundedCornerShape(8.dp)
    ) {
        content()
    }
}