package com.egbuna.cocktailapp.domain.model

data class CocktailListState(
    var isLoading: Boolean = false,
    val success: List<Cocktail> = emptyList(),
    val error: String? = null
)
