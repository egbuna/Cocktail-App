package com.egbuna.cocktailapp.data.remote.dto

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import com.egbuna.cocktailapp.domain.model.Cocktail

@Entity(
    foreignKeys = [ForeignKey(entity = Cocktail::class, parentColumns = ["id"], childColumns = ["cocktailId"], onDelete = CASCADE)]
)
data class DrinkDto(


    val cocktailId: String,
    val dateModified: String,
    val idDrink: String,
    val strAlcoholic: String,
    val strCategory: String,
    val strCreativeCommonsConfirmed: String,
    val strDrink: String,
    val strDrinkAlternate: Any,
    val strDrinkThumb: String,
    val strGlass: String,
    val strIBA: String,
    val strImageAttribution: String,
    val strImageSource: String,
    val strIngredient1: String,
    val strIngredient10: String,
    val strIngredient11: String,
    val strIngredient12: String,
    val strIngredient13: String,
    val strIngredient14: String,
    val strIngredient15: String,
    val strIngredient2: String,
    val strIngredient3: String,
    val strIngredient4: String,
    val strIngredient5: String,
    val strIngredient6: String,
    val strIngredient7: String,
    val strIngredient8: String,
    val strIngredient9: String,
    val strInstructions: String,
    val strMeasure1: String,
    val strMeasure10: String,
    val strMeasure11: String,
    val strMeasure12: String,
    val strMeasure13: String,
    val strMeasure14: String,
    val strMeasure15: String,
    val strMeasure2: String,
    val strMeasure3: String,
    val strMeasure4: String,
    val strMeasure5: String,
    val strMeasure6: String,
    val strMeasure7: String,
    val strMeasure8: String,
    val strMeasure9: String,
    val strTags: String,
    val strVideo: Any
)

fun DrinkDto.toCocktail(): Cocktail {
    val cocktail = Cocktail(
        id = idDrink,
        category = strCategory,
        drink = strDrink,
        drinkThumb = strDrinkThumb,
        imageUrl = strImageSource
    )
    cocktail.isAlcoholic = strAlcoholic
    cocktail.glass = strGlass
    cocktail.ingredients = emptyList()
    cocktail.measure = emptyList()
    cocktail.instructions = strInstructions
    cocktail.tags = strTags
    return cocktail
}