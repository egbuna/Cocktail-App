package com.egbuna.cocktailapp

import com.egbuna.cocktailapp.data.remote.dto.CocktailDto
import com.egbuna.cocktailapp.domain.model.Cocktail
import com.egbuna.cocktailapp.domain.repository.CocktailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.IllegalStateException

class FakeCocktailRepository : CocktailRepository {

    private val fakeCocktails = mutableListOf<Cocktail>()

    override suspend fun searchForCocktail(keyword: String): CocktailDto {
        throw IllegalStateException()
    }

    override suspend fun deleteCocktail(cocktail: Cocktail) {
        fakeCocktails.remove(cocktail)
    }

    override suspend fun getCocktail(id: String): Cocktail? {
        return fakeCocktails.find { it.id == id }
    }

    override fun getLocalCocktails(): Flow<List<Cocktail>> {
        return flow {
            emit(fakeCocktails)
        }
    }

    override suspend fun saveCocktail(cocktail: Cocktail) {
        fakeCocktails.add(cocktail)
    }

    override suspend fun getDrink(id: Int): CocktailDto {
        TODO("Not yet implemented")
    }

    override suspend fun getPopularCocktailDrinks(): CocktailDto {
        TODO("Not yet implemented")
    }
}