package com.egbuna.cocktailapp.domain.usecase

import com.egbuna.cocktailapp.domain.model.Cocktail
import com.egbuna.cocktailapp.domain.repository.CocktailRepository
import javax.inject.Inject

class GetLocalCocktail @Inject constructor(
    private val repository: CocktailRepository
) {

    suspend operator fun invoke(id: String): Cocktail? {
        return repository.getCocktail(id)
    }
}