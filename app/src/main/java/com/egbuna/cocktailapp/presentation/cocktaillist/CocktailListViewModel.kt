package com.egbuna.cocktailapp.presentation.cocktaillist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.egbuna.cocktailapp.common.Resource
import com.egbuna.cocktailapp.domain.model.CocktailListState
import com.egbuna.cocktailapp.domain.model.SearchCocktailState
import com.egbuna.cocktailapp.domain.usecase.GetCocktails
import com.egbuna.cocktailapp.domain.usecase.SearchCocktail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CocktailListViewModel @Inject constructor(
    private val getCocktails: GetCocktails,
    private val searchCocktail: SearchCocktail
): ViewModel() {
    private val _state = mutableStateOf(CocktailListState())
    val state: State<CocktailListState> = _state

    private val _searchState = mutableStateOf(SearchCocktailState())
    val searchState: State<SearchCocktailState> = _searchState

    private var searchJob: Job? = null

    init {
       getCocktails()
    }

    fun onTextChange(value: String) {
        if (value.isEmpty()) {
            _searchState.value = searchState.value.copy(isHintVisible = true, searchWord = "")
            getCocktails()
        } else {
            _searchState.value = searchState.value.copy(isHintVisible = false, searchWord = value)
            searchJob?.cancel()
            searchJob = viewModelScope.launch {
                delay(1000)
                searchCocktail.invoke(value).onEach { result ->
                    when(result) {
                        is Resource.Loading -> {
                            _state.value = CocktailListState(isLoading = true)
                        }
                        is Resource.Success -> {
                            _state.value = CocktailListState(success = result.data ?: emptyList(), isLoading = false)
                        }
                        is Resource.Error -> {
                            _state.value = CocktailListState(error = result.message, isLoading = false)
                        }
                    }
                }.launchIn(viewModelScope)
            }
        }
    }

    private fun getCocktails() {
        getCocktails.invoke().onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    _state.value = CocktailListState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = CocktailListState(success = result.data ?: emptyList(), isLoading = false)
                }
                is Resource.Error -> {
                    _state.value = CocktailListState(error = result.message, isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }
}