package com.egbuna.cocktailapp.presentation.cocktaillist.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import com.egbuna.cocktailapp.domain.model.Cocktail

@Composable
fun CocktailListItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onFavouriteItemClick: (Cocktail) -> Unit,
    cocktail: Cocktail
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.padding(horizontal = 2.dp, vertical = 4.dp)
    ) {
        Box(
            modifier = modifier
                .width(180.dp)
                .height(230.dp)
                .clickable {
                    onClick.invoke()
                }

        ) {
            Image(
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize(),
                painter = rememberImagePainter(cocktail.drinkThumb), contentDescription = "Cocktail image"
            )
            Text(text = cocktail.drink, color = Color.White, modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                .fillMaxWidth(),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis)
//            IconButton(
//                modifier = Modifier.constrainAs(iconButton) {
//                    top.linkTo(parent.top, margin = 8.dp)
//                    end.linkTo(parent.end)
//                },
//                onClick = {
//                          onFavouriteItemClick.invoke(cocktail)
//                },
//            ) {
//                Icon(
//                    imageVector = if (cocktail.favourited) Icons.Default.Favorite else Icons.Default.FavoriteBorder,tint = Color.White,
//                    contentDescription = "Favourite"
//                )
//            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}