package com.egbuna.cocktailapp.presentation.cocktaildetail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egbuna.cocktailapp.common.Constant.PARAM_COCKTAIL_ID
import com.egbuna.cocktailapp.common.Resource
import com.egbuna.cocktailapp.domain.model.Cocktail
import com.egbuna.cocktailapp.domain.model.CocktailState
import com.egbuna.cocktailapp.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GetCocktailViewModel @Inject constructor(
    private val getCocktail: GetCocktail,
    savedStateHandle: SavedStateHandle,
    private val getLocalCocktail: GetLocalCocktail,
    private val deleteLocalCocktail: DeleteLocalCocktail,
    private val saveCocktail: SaveCocktail
) : ViewModel() {

    private val _state = mutableStateOf(CocktailState())
    val state = _state

    private val _favourite = mutableStateOf(false)
    val favourite = _favourite

    init {
        savedStateHandle.get<String>(PARAM_COCKTAIL_ID)?.let {
            getCocktail(it)
        }
    }

    fun saveCocktail(cocktail: Cocktail) {
        viewModelScope.launch {
            saveCocktail.invoke(cocktail)
            _favourite.value = true
        }
    }

    fun deleteLocalCocktail(id: String) {
        viewModelScope.launch {
            val cocktail = getLocalCocktail.invoke(id)
            if (cocktail != null) {
                deleteLocalCocktail.invoke(cocktail)
                _favourite.value = false
            }
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
                        val cocktail = getLocalCocktail.invoke(id)
                        if (cocktail != null) {
                            _favourite.value = true
                        }
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