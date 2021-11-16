package com.egbuna.cocktailapp.domain.repository

import com.egbuna.cocktailapp.data.remote.dto.CocktailDto
import com.egbuna.cocktailapp.data.remote.dto.DrinkDto
import com.egbuna.cocktailapp.domain.model.Cocktail
import kotlinx.coroutines.flow.Flow

interface CocktailRepository {

    suspend fun searchForCocktail(keyword: String): CocktailDto
    suspend fun deleteCocktail(cocktail: Cocktail)
    suspend fun getCocktail(id: String): Cocktail?
    fun getLocalCocktails(): Flow<List<Cocktail>>
    suspend fun saveCocktail(cocktail: Cocktail)
    suspend fun getDrink(id: Int): CocktailDto
    suspend fun getPopularCocktailDrinks(): CocktailDto
}