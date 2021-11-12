package com.egbuna.cocktailapp.domain.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.egbuna.cocktailapp.data.remote.dto.DrinkDto

@Entity
data class Cocktail(
    @PrimaryKey
    val id: String,

    val category: String,

    val drink: String,

    val drinkThumb: String,

    val imageUrl: String?,

) {
    @Ignore
    var ingredients: List<DrinkDto> = emptyList()

    @Ignore
    var instructions: String = ""

    @Ignore
    var measure: List<String> = emptyList()

    @Ignore
    var tags: String? = ""

    @Ignore
    var glass: String = ""

    @Ignore
    var isAlcoholic: String = ""

    @Ignore
    var favourited: Boolean = false
}
