package com.egbuna.cocktailapp.domain.model

data class CocktailState(
    var cocktail: Cocktail? = null,
    val loading: Boolean = false,
    val error: String? = null
)
