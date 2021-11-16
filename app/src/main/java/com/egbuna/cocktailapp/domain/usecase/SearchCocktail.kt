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

class SearchCocktail @Inject constructor(
    private val repository: CocktailRepository
) {

    suspend operator fun invoke(search: String): Flow<Resource<List<Cocktail>?>> = flow {
        try {
            emit(Resource.Loading())
            val searchCocktail = repository.searchForCocktail(search).drinks?.map { it.toCocktail() }
            if (searchCocktail.isNullOrEmpty())
                emit(Resource.Error<List<Cocktail>?>(message = "No drinks found"))
            else
                emit(Resource.Success<List<Cocktail>?>(searchCocktail))

        } catch (e: HttpException) {
            emit(Resource.Error<List<Cocktail>?>(message =  e.localizedMessage ?: "Omo something went wrong"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Cocktail>?>(message = "Couldn't complete the request, Please make sure you're connected to the internet"))
        }
    }
}