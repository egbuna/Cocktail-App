package com.egbuna.cocktailapp.presentation.favourite

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egbuna.cocktailapp.domain.model.Cocktail
import com.egbuna.cocktailapp.domain.model.FavouriteCocktailState
import com.egbuna.cocktailapp.domain.usecase.GetFavouriteCocktail
import com.egbuna.cocktailapp.domain.usecase.SaveCocktail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteCocktailViewModel @Inject constructor(
    private val getFavouriteCocktail: GetFavouriteCocktail,
    private val saveCocktail: SaveCocktail
): ViewModel() {

    private val _state = mutableStateOf<FavouriteCocktailState>(FavouriteCocktailState())
    val state: State<FavouriteCocktailState> = _state

    private var getFavouriteCocktailJob: Job? = null

    init {
        getFavourites()
    }

    fun saveCocktail(cocktail: Cocktail) {
        viewModelScope.launch {
            saveCocktail.invoke(cocktail)
        }
    }

    private fun getFavourites() {
        getFavouriteCocktailJob?.cancel()
        getFavouriteCocktailJob = getFavouriteCocktail.invoke()
            .onEach { it ->
                _state.value = state.value.copy(favouriteCocktails = it.map { cocktail ->
                    cocktail.favourited = true
                    cocktail
                })
            }.launchIn(viewModelScope)
    }
}