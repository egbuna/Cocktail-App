package com.egbuna.cocktailapp.data.remote

import com.egbuna.cocktailapp.data.remote.dto.CocktailDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailApi {

    @GET("search.php")
    suspend fun getDrinks(@Query("s") search: String): CocktailDto
}