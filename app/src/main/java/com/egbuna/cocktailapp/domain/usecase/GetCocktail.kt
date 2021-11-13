package com.egbuna.cocktailapp.domain.usecase

import com.egbuna.cocktailapp.common.Resource
import com.egbuna.cocktailapp.data.remote.dto.toCocktail
import com.egbuna.cocktailapp.domain.model.Cocktail
import com.egbuna.cocktailapp.domain.repository.CocktailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCocktail @Inject constructor(
    private val cocktailRepository: CocktailRepository
) {

    operator fun invoke(id: Int): Flow<Resource<Cocktail>> = flow {
        emit(Resource.Loading<Cocktail>())
        try {
            val cocktail = cocktailRepository.getDrink(id)
            emit(Resource.Success<Cocktail>(cocktail.drinks?.map {
                it.toCocktail()
            }?.first()))
        } catch (e: HttpException) {
            emit(
                Resource.Error<Cocktail>(
                    message = e.localizedMessage ?: "Omo something went wrong"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<Cocktail>(message = "Couldn't complete the request, Please make sure you're connected to the internet"))
        }
    }
}