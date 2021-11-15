package com.egbuna.cocktailapp.data.remote

import com.egbuna.cocktailapp.data.remote.dto.CocktailDto
import com.egbuna.cocktailapp.data.remote.dto.DrinkDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailApi {

    @GET("search.php")
    suspend fun searchCocktailDrinks(@Query("s") search: String): CocktailDto

    @GET("lookup.php")
    suspend fun getDrink(@Query("i") id: Int): CocktailDto

    @GET("popular.php")
    suspend fun getPopularCocktail(): CocktailDto
}