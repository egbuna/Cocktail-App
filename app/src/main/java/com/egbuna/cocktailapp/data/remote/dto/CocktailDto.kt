package com.egbuna.cocktailapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CocktailDto(

    @SerializedName("drinks")
    val drinks: List<DrinkDto>?
)