package com.egbuna.cocktailapp.data.repository

import com.egbuna.cocktailapp.data.local.CocktailDao
import com.egbuna.cocktailapp.data.remote.CocktailApi
import com.egbuna.cocktailapp.data.remote.dto.CocktailDto
import com.egbuna.cocktailapp.data.remote.dto.DrinkDto
import com.egbuna.cocktailapp.domain.model.Cocktail
import com.egbuna.cocktailapp.domain.repository.CocktailRepository
import kotlinx.coroutines.flow.Flow
import java.lang.Exception
import javax.inject.Inject

class CocktailRepositoryImpl @Inject constructor(
    private val cocktailApi: CocktailApi,
    private val cocktailDao: CocktailDao
): CocktailRepository {
    override suspend fun getCocktails(): CocktailDto = cocktailApi.getDrinks("margarita")

    override suspend fun searchForCocktail(keyword: String): List<CocktailDto> {
        return emptyList()
    }

    override suspend fun deleteCocktail(cocktail: Cocktail) {
        cocktailDao.deleteCocktail(cocktail)
    }

    override suspend fun getCocktail(id: String): Cocktail? {
        return cocktailDao.getCocktailById(id)
    }

    override fun getLocalCocktails(): Flow<List<Cocktail>> {
        return cocktailDao.getCocktails()
    }

    override suspend fun saveCocktail(cocktail: Cocktail) {
        cocktailDao.insertCocktail(cocktail)
    }

    override suspend fun getDrink(id: Int): CocktailDto {
        return cocktailApi.getDrink(id)
    }
}