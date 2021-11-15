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

class GetCocktails @Inject constructor(
    private val cocktailRepository: CocktailRepository
) {
     operator fun invoke(): Flow<Resource<List<Cocktail>?>> = flow {
         try {
             emit(Resource.Loading<List<Cocktail>?>())
             val cocktails = cocktailRepository.getPopularCocktailDrinks().drinks?.map { it.toCocktail() }
             if (cocktails.isNullOrEmpty().not())
                 emit(Resource.Success<List<Cocktail>?>(cocktails))
             else
                 emit(Resource.Error<List<Cocktail>?>(message = "No drinks found"))
         } catch (e: HttpException) {
             emit(Resource.Error<List<Cocktail>?>(message =  e.localizedMessage ?: "Omo something went wrong"))
         } catch (e: IOException) {
             emit(Resource.Error<List<Cocktail>?>(message = "Couldn't complete the request, Please make sure you're connected to the internet"))
         }
     }
}