package com.egbuna.cocktailapp.domain.usecase

import com.egbuna.cocktailapp.domain.model.Cocktail
import com.egbuna.cocktailapp.domain.repository.CocktailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavouriteCocktail @Inject constructor(
    private val cocktailRepository: CocktailRepository
) {

    operator fun invoke(): Flow<List<Cocktail>>{
        return cocktailRepository.getLocalCocktails()
    }
}