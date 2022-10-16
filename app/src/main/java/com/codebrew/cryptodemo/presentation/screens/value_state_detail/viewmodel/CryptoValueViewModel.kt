package com.codebrew.cryptodemo.presentation.screens.value_state_detail.viewmodel


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codebrew.cryptodemo.common.Result
import com.codebrew.cryptodemo.domain.use_cases.ValueStateUseCase
import com.codebrew.cryptodemo.presentation.utils.CryptoDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CryptoValueViewModel @Inject constructor(
    private val valueStateUseCase: ValueStateUseCase
): ViewModel() {

    private val _state = mutableStateOf(CryptoDataState())
    val state: State<CryptoDataState> = _state

    init {
        getValueStateData()
    }

    private fun getValueStateData() {
        valueStateUseCase().onEach { result ->
            when(result) {
                is Result.Success -> _state.value = CryptoDataState(cryptoData = result.data)
                is Result.Error -> _state.value = CryptoDataState(error = result.message?: "Something went wrong")
                is Result.Loading -> _state.value = CryptoDataState(isLoading = true)
            }
        }.launchIn(viewModelScope)
    }

}