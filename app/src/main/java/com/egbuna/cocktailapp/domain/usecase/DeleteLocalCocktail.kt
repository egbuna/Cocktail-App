package com.egbuna.cocktailapp.domain.usecase

import com.egbuna.cocktailapp.domain.model.Cocktail
import com.egbuna.cocktailapp.domain.repository.CocktailRepository
import javax.inject.Inject

class DeleteLocalCocktail @Inject constructor(
    private val cocktailRepository: CocktailRepository
) {

    suspend operator fun invoke(cocktail: Cocktail) {
        cocktailRepository.deleteCocktail(cocktail)
    }
}