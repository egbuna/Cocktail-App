package com.egbuna.cocktailapp.common

import com.egbuna.cocktailapp.BuildConfig

object Constant {
    const val BASE_URL = "https://www.thecocktaildb.com/api/json/${BuildConfig.COCKTAIL_API_VERSION}/${BuildConfig.COCKTAIL_API_KEY}/"
    const val PARAM_COCKTAIL_ID = "cocktailId"
}