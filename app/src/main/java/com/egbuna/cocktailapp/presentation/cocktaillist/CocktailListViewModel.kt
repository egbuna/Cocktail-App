package com.egbuna.cocktailapp.presentation.cocktaillist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egbuna.cocktailapp.common.Resource
import com.egbuna.cocktailapp.domain.model.CocktailListState
import com.egbuna.cocktailapp.domain.usecase.GetCocktails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CocktailListViewModel @Inject constructor(private val getCocktails: GetCocktails): ViewModel() {
    private val _state = mutableStateOf(CocktailListState())
    val state: State<CocktailListState> = _state

    init {
       getCocktails()
    }

    private fun getCocktails() {
        getCocktails.invoke().onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    _state.value = CocktailListState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = CocktailListState(success = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = CocktailListState(error = result.message)
                }
            }
        }.launchIn(viewModelScope)
    }
}