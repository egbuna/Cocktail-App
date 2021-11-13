package com.egbuna.cocktailapp.presentation.cocktaildetail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egbuna.cocktailapp.common.Constant.PARAM_COCKTAIL_ID
import com.egbuna.cocktailapp.common.Resource
import com.egbuna.cocktailapp.domain.model.CocktailState
import com.egbuna.cocktailapp.domain.usecase.GetCocktail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetCocktailViewModel @Inject constructor(
    private val getCocktail: GetCocktail,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CocktailState())
    val state = _state

    init {
        savedStateHandle.get<String>(PARAM_COCKTAIL_ID)?.let {
            getCocktail(it)
        }
    }

    private fun getCocktail(id: String) {
        viewModelScope.launch {
            getCocktail.invoke(id.toInt()).onEach {
                when(it) {
                    is Resource.Loading -> {
                        _state.value = state.value.copy(loading = true)
                    }
                    is Resource.Success -> {
                        _state.value = state.value.copy(cocktail = it.data, loading = false)
                    }
                    is Resource.Error -> {
                        _state.value = state.value.copy(error = it.message, loading = false)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}