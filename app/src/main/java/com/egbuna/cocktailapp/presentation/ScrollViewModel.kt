package com.egbuna.cocktailapp.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScrollViewModel @Inject constructor() : ViewModel() {

    private val _state = mutableStateOf(false)
    val state = _state

    fun isScrolling(value: Boolean) {
        _state.value = value
    }
}