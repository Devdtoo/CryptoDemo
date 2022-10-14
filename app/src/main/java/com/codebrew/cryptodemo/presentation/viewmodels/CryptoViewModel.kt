package com.codebrew.cryptodemo.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codebrew.cryptodemo.domain.use_cases.EmptyStateUseCase
import com.codebrew.cryptodemo.domain.use_cases.ValueStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoViewModel @Inject constructor(
    val emptyStateUseCase: EmptyStateUseCase,
    val valueStateUseCase: ValueStateUseCase
): ViewModel() {

    fun getEmptyStateData() {
        viewModelScope.launch(Dispatchers.IO) {

        }
    }

    fun getValueStateData() {
        viewModelScope.launch(Dispatchers.IO) {

        }
    }

}